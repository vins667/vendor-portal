import { Component, OnInit } from '@angular/core';
import * as d3 from 'd3';
import { EmployeeViewService } from 'app/entities/employee-view';
import * as saveSvgAsPng from 'save-svg-as-png/lib/saveSvgAsPng';
import * as FileSaver from 'file-saver';

@Component({
  selector: 'jhi-employee-hierarchy',
  templateUrl: './employee-hierarchy.component.html'
})
export class EmployeeHierarchyComponent implements OnInit {
  public params = {
    selector: '#svgChart',
    chartWidth: window.innerWidth - 320,
    chartHeight: window.innerHeight - 150,
    funcs: {
      showMySelf: null,
      search: null,
      closeSearchBox: null,
      clearResult: null,
      findInTree: null,
      reflectResults: null,
      departmentClick: null,
      back: null,
      toggleFullScreen: null,
      locate: null
    },
    data: null,
    pristinaData: null,
    searchData: null
  };

  searchResult: any;
  isDownloading = false;

  constructor(private emplyeeViewService: EmployeeViewService) {}

  ngOnInit() {
    this.searchResult = [];
    this.emplyeeViewService.hierarchy().subscribe(hierarchy => {
      this.params.data = hierarchy.body;
      this.params.pristinaData = JSON.parse(JSON.stringify(hierarchy.body));
      this.drawOrganizationChart(this.params);
    });
  }

  reflectResults(results) {
    this.params.searchData = results;
  }

  drawOrganizationChart(params) {
    listen();

    params.funcs.showMySelf = showMySelf;
    params.funcs.expandAll = expandAll;
    params.funcs.search = searchUsers;
    params.funcs.closeSearchBox = closeSearchBox;
    params.funcs.findInTree = findInTree;
    params.funcs.clearResult = clearResult;
    params.funcs.reflectResults = this.reflectResults;
    params.funcs.departmentClick = departmentClick;
    params.funcs.back = back;
    params.funcs.toggleFullScreen = toggleFullScreen;
    params.funcs.locate = locate;

    const attrs = {
      EXPAND_SYMBOL: '\uf067',
      COLLAPSE_SYMBOL: '\uf068',
      selector: params.selector,
      root: params.data,
      width: params.chartWidth,
      height: params.chartHeight,
      index: 0,
      nodePadding: 9,
      collapseCircleRadius: 7,
      nodeHeight: 100,
      nodeWidth: 210,
      duration: 750,
      rootNodeTopMargin: 20,
      minMaxZoomProportions: [0.05, 3],
      linkLineSize: 180,
      collapsibleFontSize: '10px',
      userIcon: '\uf007',
      nodeStroke: '#ccc',
      nodeStrokeWidth: '1px'
    };

    const dynamic = {
      nodeImageWidth: (attrs.nodeHeight * 100) / 140,
      nodeImageHeight: attrs.nodeHeight - 2 * attrs.nodePadding,
      nodeTextLeftMargin: 0,
      rootNodeLeftMargin: attrs.width / 2,
      nodePositionNameTopMargin: 0,
      nodeChildCountTopMargin: 0
    };

    dynamic.nodeTextLeftMargin = attrs.nodePadding * 2 + dynamic.nodeImageWidth;
    dynamic.nodePositionNameTopMargin = attrs.nodePadding + 4 + (dynamic.nodeImageHeight / 4) * 1;
    dynamic.nodeChildCountTopMargin = attrs.nodePadding + 14 + (dynamic.nodeImageHeight / 4) * 3;

    const tree = d3.layout.tree().nodeSize([attrs.nodeWidth + 40, attrs.nodeHeight]);
    const diagonal = d3.svg.diagonal().projection(function(d) {
      // debugger;
      return [d.x + attrs.nodeWidth / 2, d.y + attrs.nodeHeight / 2];
    });

    const zoomBehaviours = d3.behavior
      .zoom()
      .scaleExtent(attrs.minMaxZoomProportions)
      .on('zoom', redraw);

    const svg = d3
      .select(attrs.selector)
      .append('svg')
      .attr('id', 'svgChartChild')
      .attr('width', attrs.width)
      .attr('height', attrs.height)
      .call(zoomBehaviours)
      .append('g')
      .attr('transform', 'translate(' + attrs.width / 2 + ',' + 20 + ')');

    // necessary so that zoom knows where to zoom and unzoom from
    zoomBehaviours.translate([dynamic.rootNodeLeftMargin, attrs.rootNodeTopMargin]);

    attrs.root.x0 = 0;
    attrs.root.y0 = dynamic.rootNodeLeftMargin;

    if (params.mode !== 'department') {
      // adding unique values to each node recursively
      let uniq = 1;
      addPropertyRecursive(
        'uniqueIdentifier',
        function(v) {
          return uniq++;
        },
        attrs.root
      );
    }

    expand(attrs.root);
    if (attrs.root.children) {
      attrs.root.children.forEach(collapse);
    }

    update(attrs.root, params);

    d3.select(attrs.selector).style('height', attrs.height);

    const tooltip = d3
      .select('body')
      .append('div')
      .attr('class', 'customTooltip-wrapper');

    function update(source, param) {
      // Compute the new tree layout.
      const nodes = tree.nodes(attrs.root).reverse(),
        links = tree.links(nodes);

      // Normalize for fixed-depth.
      nodes.forEach(function(d) {
        d.y = d.depth * attrs.linkLineSize;
      });

      // Update the nodes…
      const node = svg.selectAll('g.node').data(nodes, function(d) {
        return d.id || (d.id = ++attrs.index);
      });

      // Enter any new nodes at the parent's previous position.
      const nodeEnter = node
        .enter()
        .append('g')
        .attr('class', 'node')
        .attr('transform', function(d) {
          return 'translate(' + source.x0 + ',' + source.y0 + ')';
        });

      const nodeGroup = nodeEnter.append('g').attr('class', 'node-group');

      nodeGroup
        .append('rect')
        .attr('width', attrs.nodeWidth)
        .attr('height', attrs.nodeHeight)
        .attr('data-node-group-id', function(d) {
          return d.uniqueIdentifier;
        })
        .attr('class', function(d) {
          let res = '';
          if (d.loggedUser) {
            res += 'nodeRepresentsCurrentUser ';
          }
          res += d._children || d.children ? 'nodeHasChildren' : 'nodeDoesNotHaveChildren';
          return res;
        });

      const collapsiblesWrapper = nodeEnter.append('g').attr('data-id', function(v) {
        return v.uniqueIdentifier;
      });

      const collapsibleRects = collapsiblesWrapper
        .append('rect')
        .attr('class', 'node-collapse-right-rect')
        .attr('height', attrs.collapseCircleRadius)
        .attr('fill', 'black')
        .attr('x', attrs.nodeWidth - attrs.collapseCircleRadius)
        .attr('y', attrs.nodeHeight - 7)
        .attr('width', function(d) {
          if (d.children || d._children) {
            return attrs.collapseCircleRadius;
          }
          return 0;
        });

      const collapsibles = collapsiblesWrapper
        .append('circle')
        .attr('class', 'node-collapse')
        .attr('cx', attrs.nodeWidth - attrs.collapseCircleRadius)
        .attr('cy', attrs.nodeHeight - 7)
        .attr('', setCollapsibleSymbolProperty);

      // hide collapse rect when node does not have children
      collapsibles
        .attr('r', function(d) {
          if (d.children || d._children) {
            return attrs.collapseCircleRadius;
          }
          return 0;
        })
        .attr('height', attrs.collapseCircleRadius);

      collapsiblesWrapper
        .append('text')
        .attr('class', 'text-collapse')
        .attr('x', attrs.nodeWidth - attrs.collapseCircleRadius)
        .attr('y', attrs.nodeHeight - 3)
        .attr('width', attrs.collapseCircleRadius)
        .attr('height', attrs.collapseCircleRadius)
        .style('font-size', attrs.collapsibleFontSize)
        .attr('text-anchor', 'middle')
        .style('font-family', 'FontAwesome')
        .text(function(d) {
          return d.collapseText;
        });

      collapsiblesWrapper.on('click', click);

      nodeGroup
        .append('text')
        .attr('x', dynamic.nodeTextLeftMargin)
        .attr('y', attrs.nodePadding + 10)
        .attr('class', 'emp-name')
        .attr('text-anchor', 'left')
        .text(function(d) {
          return d.name.trim();
        })
        .call(wrap, attrs.nodeWidth);

      nodeGroup
        .append('text')
        .attr('x', dynamic.nodeTextLeftMargin)
        .attr('y', dynamic.nodePositionNameTopMargin)
        .attr('class', 'emp-position-name')
        .attr('dy', '.35em')
        .attr('text-anchor', 'left')
        .text(function(d) {
          let position = d.positionName.substring(0, 21);
          if (position.length < d.positionName.length) {
            position = position.substring(0, 18) + '...';
          }
          return position;
        });

      nodeGroup
        .append('text')
        .attr('x', dynamic.nodeTextLeftMargin)
        .attr('y', attrs.nodePadding + (dynamic.nodeImageHeight / 4) * 2)
        .attr('class', 'emp-department')
        .attr('dy', '.35em')
        .attr('text-anchor', 'left')
        .text(function(d) {
          return d.unit.desc;
        });

      nodeGroup
        .append('text')
        .attr('x', dynamic.nodeTextLeftMargin)
        .attr('y', attrs.nodePadding + 15 + (dynamic.nodeImageHeight / 4) * 2)
        .attr('class', 'emp-area')
        .attr('dy', '.35em')
        .attr('text-anchor', 'left')
        .text(function(d) {
          return d.area;
        });

      nodeGroup
        .append('text')
        .attr('x', dynamic.nodeTextLeftMargin)
        .attr('y', dynamic.nodeChildCountTopMargin)
        .attr('class', 'emp-count-icon')
        .attr('text-anchor', 'left')
        .style('font-family', 'FontAwesome')
        .text(function(d) {
          if (d.children || d._children) {
            return attrs.userIcon;
          }
        });

      nodeGroup
        .append('text')
        .attr('x', dynamic.nodeTextLeftMargin + 13)
        .attr('y', dynamic.nodeChildCountTopMargin)
        .attr('class', 'emp-count')
        .attr('text-anchor', 'left')
        .text(function(d) {
          if (d.children) {
            return d.children.length;
          }
          if (d._children) {
            return d._children.length;
          }
          return;
        });

      nodeGroup
        .append('defs')
        .append('svg:clipPath')
        .attr('id', 'clip')
        .append('svg:rect')
        .attr('id', 'clip-rect')
        .attr('rx', 3)
        .attr('x', attrs.nodePadding)
        .attr('y', 2 + attrs.nodePadding)
        .attr('width', dynamic.nodeImageWidth)
        .attr('fill', 'none')
        .attr('height', dynamic.nodeImageHeight - 4);

      nodeGroup
        .append('svg:image')
        .attr('y', 2 + attrs.nodePadding)
        .attr('x', attrs.nodePadding)
        .attr('preserveAspectRatio', 'none')
        .attr('width', dynamic.nodeImageWidth)
        .attr('height', dynamic.nodeImageHeight - 4)
        .attr('clip-path', 'url(#clip)')
        .attr('xlink:href', function(v) {
          return v.imageUrl;
        });

      // Transition nodes to their new position.
      const nodeUpdate = node
        .transition()
        .duration(attrs.duration)
        .attr('transform', function(d) {
          return 'translate(' + d.x + ',' + d.y + ')';
        });

      // todo replace with attrs object
      nodeUpdate
        .select('rect')
        .attr('width', attrs.nodeWidth)
        .attr('height', attrs.nodeHeight)
        .attr('rx', 3)
        .attr('stroke', function(d) {
          if (param && d.uniqueIdentifier === param.locate) {
            return '#a1ceed';
          }
          return attrs.nodeStroke;
        })
        .attr('stroke-width', function(d) {
          if (param && d.uniqueIdentifier === param.locate) {
            return 6;
          }
          return attrs.nodeStrokeWidth;
        });

      // Transition exiting nodes to the parent's new position.
      const nodeExit = node
        .exit()
        .transition()
        .duration(attrs.duration)
        .attr('transform', function(d) {
          return 'translate(' + source.x + ',' + source.y + ')';
        })
        .remove();

      nodeExit
        .select('rect')
        .attr('width', attrs.nodeWidth)
        .attr('height', attrs.nodeHeight);

      // Update the links…
      const link = svg.selectAll('path.link').data(links, function(d) {
        return d.target.id;
      });

      // Enter any new links at the parent's previous position.
      link
        .enter()
        .insert('path', 'g')
        .attr('class', 'link')
        .attr('x', attrs.nodeWidth / 2)
        .attr('y', attrs.nodeHeight / 2)
        .attr('d', function(d) {
          const o = {
            x: source.x0,
            y: source.y0
          };
          return diagonal({
            source: o,
            target: o
          });
        });

      // Transition links to their new position.
      link
        .transition()
        .duration(attrs.duration)
        .attr('d', diagonal);

      // Transition exiting nodes to the parent's new position.
      link
        .exit()
        .transition()
        .duration(attrs.duration)
        .attr('d', function(d) {
          const o = {
            x: source.x,
            y: source.y
          };
          return diagonal({
            source: o,
            target: o
          });
        })
        .remove();

      // Stash the old positions for transition.
      nodes.forEach(function(d) {
        d.x0 = d.x;
        d.y0 = d.y;
      });

      if (param && param.locate) {
        let x;
        let y;

        nodes.forEach(function(d) {
          if (d.uniqueIdentifier === param.locate) {
            x = d.x;
            y = d.y;
          }
        });

        // normalize for width/height
        const new_x = -x + window.innerWidth / 2;
        const new_y = -y + window.innerHeight / 2;

        // move the main container g
        svg.attr('transform', 'translate(' + new_x + ',' + new_y + ')');
        zoomBehaviours.translate([new_x, new_y]);
        zoomBehaviours.scale(1);
      }

      if (param && param.centerMySelf) {
        let lx;
        let ly;

        nodes.forEach(function(d) {
          if (d.loggedUser) {
            lx = d.x;
            ly = d.y;
          }
        });

        // normalize for width/height
        const new_x_l = -lx + window.innerWidth / 2;
        const new_y_l = -ly + window.innerHeight / 2;

        // move the main container g
        svg.attr('transform', 'translate(' + new_x_l + ',' + new_y_l + ')');
        zoomBehaviours.translate([new_x_l, new_y_l]);
        zoomBehaviours.scale(1);
      }

      /*################  TOOLTIP  #############################*/

      function getTagsFromCommaSeparatedStrings(tags) {
        return tags
          .split(',')
          .map(function(v) {
            return "<li><div class='tag'>" + v + '</div></li>  ';
          })
          .join('');
      }

      function tooltipContent(item) {
        let strVar = '';

        strVar += "  <div class='customTooltip'>";
        strVar += '    <!--';
        strVar +=
          "    <div class='tooltip-image-wrapper'> <img width='300' " +
          " src='https://raw.githubusercontent.com/bumbeishvili/Assets/master/Projects/D3/Organization%20Chart/cto.jpg'> </div>";
        strVar += '-->';
        strVar += "    <div class='profile-image-wrapper'>";
        strVar += "    <img src='" + item.imageUrl + "' style='width: 100%; height: 100%;'>";
        strVar += '    </div>';
        strVar += "    <div class='tooltip-hr'></div>";
        strVar += "    <div class='tooltip-desc'>";
        strVar += "      <div class='col-md-12 name'> " + item.name + '</div>';
        strVar += "      <div class='col-md-12 position'>" + item.positionName + ' </div>';
        strVar += "      <div class='col-md-12 area'><i class=' fa fa-phone '></i> " + item.area + ' </div>';
        strVar += "      <div class='col-md-12'><div class='btn-group'>";
        strVar +=
          "      <button type='button' class='btn btn-blush btn-sm btn-round' style='line-height: 1.5;padding-left:10px; padding-right: 10px;'>";
        strVar += item.unit.desc;
        strVar += '</button> </div>';
        strVar += "      <h4 class='tags-wrapper'>             <span class='title'><i class='fa fa-tags' aria-hidden='true'></i>";
        strVar += '        ';
        strVar +=
          "        </span>           <ul class='tags'>" + getTagsFromCommaSeparatedStrings(item.tags) + '</ul>         </h4> </div>';
        strVar += "    <div class='bottom-tooltip-hr'></div>";
        strVar += '  </div>';
        strVar += '';

        return strVar;
      }

      function tooltipHoverHandler(d) {
        const content = tooltipContent(d);
        tooltip.html(content);

        tooltip
          .transition()
          .duration(200)
          .style('opacity', '1')
          .style('display', 'block');
        d3.select(this)
          .attr('cursor', 'pointer')
          .attr('stroke-width', 50);

        let ly = d3.event.pageY;
        let lx = d3.event.pageX;

        // restrict tooltip to fit in borders
        if (ly < 220) {
          ly += 220 - ly;
          lx += 130;
        }

        if (ly > attrs.height - 300) {
          ly -= 300 - (attrs.height - ly);
        }

        tooltip.style('top', ly - 300 + 'px').style('left', lx - 470 + 'px');
      }

      function tooltipOutHandler() {
        tooltip
          .transition()
          .duration(200)
          .style('opacity', '0')
          .style('display', 'none');
        d3.select(this).attr('stroke-width', 5);
      }

      nodeGroup.on('click', tooltipHoverHandler);

      nodeGroup.on('dblclick', tooltipOutHandler);

      function equalToEventTarget() {
        return this === d3.event.target;
      }

      d3.select('body').on('click', function() {
        const outside = tooltip.filter(equalToEventTarget).empty();
        if (outside) {
          tooltip.style('opacity', '0').style('display', 'none');
        }
      });
    }

    // Toggle children on click.
    function click(d) {
      d3.select(this)
        .select('text')
        .text(function(dv) {
          if (dv.collapseText === attrs.EXPAND_SYMBOL) {
            dv.collapseText = attrs.COLLAPSE_SYMBOL;
          } else {
            if (dv.children) {
              dv.collapseText = attrs.EXPAND_SYMBOL;
            }
          }
          return dv.collapseText;
        });

      if (d.children) {
        d._children = d.children;
        d.children = null;
      } else {
        d.children = d._children;
        d._children = null;
      }
      update(d, params);
    }

    // ########################################################

    // Redraw for zoom
    function redraw() {
      // console.log('here', d3.event.translate, d3.event.scale);
      svg.attr('transform', 'translate(' + d3.event.translate + ')' + ' scale(' + d3.event.scale + ')');
    }

    // #############################   Function Area #######################
    function wrap(text, width) {
      text.each(function() {
        const text_l = d3.select(this);
        const words = text_l
          .text()
          .split(/\s+/)
          .reverse();
        let word;
        let line = [];
        let lineNumber = 0;
        const lineHeight = 1.1; // ems
        const x = text_l.attr('x');
        const y = text_l.attr('y');
        const dy = 0; // parseFloat(text.attr('dy')),
        let tspan = text_l
          .text(null)
          .append('tspan')
          .attr('x', x)
          .attr('y', y)
          .attr('dy', dy + 'em');
        while ((word = words.pop())) {
          line.push(word);
          tspan.text(line.join(' '));
          if (tspan.node().getComputedTextLength() > width) {
            line.pop();
            tspan.text(line.join(' '));
            line = [word];
            tspan = text_l
              .append('tspan')
              .attr('x', x)
              .attr('y', y)
              .attr('dy', ++lineNumber * lineHeight + dy + 'em')
              .text(word);
          }
        }
      });
    }

    function addPropertyRecursive(propertyName, propertyValueFunction, element) {
      if (element[propertyName]) {
        element[propertyName] = element[propertyName] + ' ' + propertyValueFunction(element);
      } else {
        element[propertyName] = propertyValueFunction(element);
      }
      if (element.children) {
        element.children.forEach(function(v) {
          addPropertyRecursive(propertyName, propertyValueFunction, v);
        });
      }
      if (element._children) {
        element._children.forEach(function(v) {
          addPropertyRecursive(propertyName, propertyValueFunction, v);
        });
      }
    }

    function departmentClick(item) {
      hide(['.customTooltip-wrapper']);

      if (item.type === 'department' && params.mode !== 'department') {
        // find third level department head user
        let found = false;
        const secondLevelChildren = params.pristinaData.children;
        parentLoop: for (let i = 0; i < secondLevelChildren.length; i++) {
          const secondLevelChild = secondLevelChildren[i];
          const thirdLevelChildren = secondLevelChild.children ? secondLevelChild.children : secondLevelChild._children;

          for (let j = 0; j < thirdLevelChildren.length; j++) {
            const thirdLevelChild = thirdLevelChildren[j];
            if (thirdLevelChild.unit.value.trim() === item.value.trim()) {
              clear(params.selector);

              hide(['.btn-action']);
              show(['.btn-action.btn-back', '.btn-action.btn-fullscreen', '.department-information']);
              set('.dept-name', item.value);

              set('.dept-emp-count', 'Employees Quantity - ' + getEmployeesCount(thirdLevelChild));
              set('.dept-description', thirdLevelChild.unit.desc);

              params.oldData = params.data;

              params.data = deepClone(thirdLevelChild);
              found = true;
              break parentLoop;
            }
          }
        }
        if (found) {
          params.mode = 'department';
          params.funcs.closeSearchBox();
          this.drawOrganizationChart(params);
        }
      }
    }

    function getEmployeesCount(node) {
      let count = 1;
      countChilds(node);
      return count;

      function countChilds(nodec) {
        const childs = nodec.children ? nodec.children : nodec._children;
        if (childs) {
          childs.forEach(function(v) {
            count++;
            countChilds(v);
          });
        }
      }
    }

    function reflectResults(results) {
      this.searchResult = results;
      const htmlStringArray = results.map(function(result) {
        let strVar = '';
        strVar += "         <div class='list-item'>";
        strVar += '          <a >';
        strVar += "            <div class='image-wrapper'>";
        strVar += "              <img class='image' src='" + result.imageUrl + "'/>";
        strVar += '            </div>';
        strVar += "            <div class='description'>";
        strVar += "              <p class='name'>" + result.name + '</p>';
        strVar += "               <p class='position-name'>" + result.positionName + '</p>';
        strVar += "               <p class='area'>" + result.area + '</p>';
        strVar += '            </div>';
        strVar += "            <div class='buttons'>";
        strVar +=
          '              <button class="btn btn-blush btn-sm btn-round" identity="' +
          result.uniqueIdentifier +
          '"' +
          ' onclick="params.funcs.locate(' +
          result.uniqueIdentifier +
          ')">Locate</button>';
        strVar += '            </div>';
        strVar += '          </a>';
        strVar += '        </div>';

        return strVar;
      });

      const htmlString = htmlStringArray.join('');
      params.funcs.clearResult();

      const parentElement = get('.result-list');
      const old = parentElement.innerHTML;
      const newElement = htmlString + old;
      parentElement.innerHTML = newElement;
      set('.user-search-box .result-header', 'RESULT - ' + htmlStringArray.length);
    }

    function clearResult() {
      // set('.result-list', "<div class='buffer' ></div>");
      // set('.user-search-box .result-header', 'RESULT');
    }

    function listen() {
      const input = get('.user-search-box .search-input');

      input.addEventListener('input', function() {
        const value = input.value ? input.value.trim() : '';
        if (value.length < 3) {
          params.funcs.clearResult();
          params.searchData = [];
        } else {
          const searchResult = params.funcs.findInTree(params.data, value);
          params.searchData = searchResult;
          params.funcs.reflectResults(searchResult);
        }
      });
    }

    function searchUsers() {
      d3.selectAll('.user-search-box')
        .transition()
        .duration(250)
        .style('width', '280px');
    }

    function closeSearchBox() {
      d3.selectAll('.user-search-box')
        .transition()
        .duration(250)
        .style('width', '0px')
        .each('end', function() {
          params.funcs.clearResult();
          clear('.search-input');
        });
    }

    function findInTree(rootElement, searchText) {
      const result = [];
      // use regex to achieve case insensitive search and avoid string creation using toLowerCase method
      const regexSearchWord = new RegExp(searchText, 'i');

      recursivelyFindIn(rootElement, searchText);

      return result;

      function recursivelyFindIn(user, searchTextLocal) {
        if (user.name.match(regexSearchWord) || user.tags.match(regexSearchWord)) {
          result.push(user);
        }

        const childUsers = user.children ? user.children : user._children;
        if (childUsers) {
          childUsers.forEach(function(childUser) {
            recursivelyFindIn(childUser, searchTextLocal);
          });
        }
      }
    }

    function back() {
      show(['.btn-action']);
      hide(['.customTooltip-wrapper', '.btn-action.btn-back', '.department-information']);
      clear(params.selector);

      params.mode = 'full';
      params.data = deepClone(params.pristinaData);
      this.drawOrganizationChart(params);
    }

    function expandAll(root) {
      expand(root);
      update(root, params);
    }

    function expand(d) {
      if (d.children) {
        d.children.forEach(expand);
      }

      if (d._children) {
        d.children = d._children;
        d.children.forEach(expand);
        d._children = null;
      }

      if (d.children) {
        // if node has children and it's expanded, then  display -
        setToggleSymbol(d, attrs.COLLAPSE_SYMBOL);
      }
    }

    function collapse(d) {
      if (d._children) {
        d._children.forEach(collapse);
      }
      if (d.children) {
        d._children = d.children;
        d._children.forEach(collapse);
        d.children = null;
      }

      if (d._children) {
        // if node has children and it's collapsed, then  display +
        setToggleSymbol(d, attrs.EXPAND_SYMBOL);
      }
    }

    function setCollapsibleSymbolProperty(d) {
      if (d._children) {
        d.collapseText = attrs.EXPAND_SYMBOL;
      } else if (d.children) {
        d.collapseText = attrs.COLLAPSE_SYMBOL;
      }
    }

    function setToggleSymbol(d, symbol) {
      d.collapseText = symbol;
      d3.select("*[data-id='" + d.uniqueIdentifier + "']")
        .select('text')
        .text(symbol);
    }

    /* recursively find logged user in subtree */
    function findmySelf(d) {
      if (d.loggedUser) {
        expandParents(d);
      } else if (d._children) {
        d._children.forEach(function(ch) {
          ch.parent = d;
          findmySelf(ch);
        });
      } else if (d.children) {
        d.children.forEach(function(ch) {
          ch.parent = d;
          findmySelf(ch);
        });
      }
    }

    function locateRecursive(d, id) {
      if (d.uniqueIdentifier === id) {
        expandParents(d);
      } else if (d._children) {
        d._children.forEach(function(ch) {
          ch.parent = d;
          locateRecursive(ch, id);
        });
      } else if (d.children) {
        d.children.forEach(function(ch) {
          ch.parent = d;
          locateRecursive(ch, id);
        });
      }
    }

    /* expand current nodes collapsed parents */
    function expandParents(d) {
      while (d.parent) {
        d = d.parent;
        if (!d.children) {
          d.children = d._children;
          d._children = null;
          setToggleSymbol(d, attrs.COLLAPSE_SYMBOL);
        }
      }
    }

    function toggleFullScreen(document) {
      if (
        (document.fullScreenElement && document.fullScreenElement !== null) ||
        (!document.mozFullScreen && !document.webkitIsFullScreen)
      ) {
        if (document.documentElement.requestFullScreen) {
          document.documentElement.requestFullScreen();
        } else if (document.documentElement.mozRequestFullScreen) {
          document.documentElement.mozRequestFullScreen();
        } else if (document.documentElement.webkitRequestFullScreen) {
          // document.documentElement.webkitRequestFullScreen(Element.ALLOW_KEYBOARD_INPUT);
        }
        d3.select(params.selector + ' svg')
          .attr('width', screen.width)
          .attr('height', screen.height);
      } else {
        if (document.cancelFullScreen) {
          document.cancelFullScreen();
        } else if (document.mozCancelFullScreen) {
          document.mozCancelFullScreen();
        } else if (document.webkitCancelFullScreen) {
          document.webkitCancelFullScreen();
        }
        d3.select(params.selector + ' svg')
          .attr('width', params.chartWidth)
          .attr('height', params.chartHeight);
      }
    }

    function showMySelf() {
      /* collapse all and expand logged user nodes */
      if (!attrs.root.children) {
        if (!attrs.root.loggedUser) {
          attrs.root.children = attrs.root._children;
        }
      }
      if (attrs.root.children) {
        attrs.root.children.forEach(collapse);
        attrs.root.children.forEach(findmySelf);
      }

      update(attrs.root, { centerMySelf: true });
    }

    // locateRecursive
    function locate(id) {
      /* collapse all and expand logged user nodes */
      if (!attrs.root.children) {
        if (!attrs.root.uniqueIdentifier === id) {
          attrs.root.children = attrs.root._children;
        }
      }
      if (attrs.root.children) {
        attrs.root.children.forEach(collapse);
        attrs.root.children.forEach(function(ch) {
          locateRecursive(ch, id);
        });
      }
      update(attrs.root, { locate: id });
      params.funcs.closeSearchBox();
    }

    function deepClone(item) {
      return JSON.parse(JSON.stringify(item));
    }

    function show(selectors) {
      display(selectors, 'initial');
    }

    function hide(selectors) {
      display(selectors, 'none');
    }

    function display(selectors, displayProp) {
      selectors.forEach(function(selector) {
        const elements = getAll(selector);
        elements.forEach(function(element) {
          element.style.display = displayProp;
        });
      });
    }

    function set(selector, value) {
      const elements = getAll(selector);
      elements.forEach(function(element) {
        element.innerHTML = value;
        element.value = value;
      });
    }

    function clear(selector) {
      set(selector, '');
    }

    function get(selector) {
      return document.querySelector(selector);
    }

    function getAll(selector) {
      return document.querySelectorAll(selector);
    }
  }

  exportImage() {
    const bBox = document
      .getElementById('svgChartChild')
      .getElementsByTagName('g')[0]
      .getBBox();
    saveSvgAsPng.saveSvgAsPng(document.getElementById('svgChartChild'), 'diagram.png', { scale: 10 }); // , width: bBox.width, height: bBox.height, top: 0, left: 0
  }

  download(cardNo) {
    if (cardNo) {
      this.isDownloading = true;
      this.emplyeeViewService.download(cardNo).subscribe(
        res => {
          FileSaver.saveAs(res, 'EmployeeHiearchy.pdf');
          this.isDownloading = false;
        },
        res => {
          this.isDownloading = false;
        }
      );
    }
  }
}
