package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.domain.MenuAccessMaster;
import io.vamani.application.domain.MenuMaster;
import io.vamani.application.domain.User;
import io.vamani.application.model.MenuDetail;
import io.vamani.application.model.MenuSave;
import io.vamani.application.model.MenuSearch;
import io.vamani.application.model.TreeViewItem;
import io.vamani.application.mssql.domain.EmployeeView;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.repository.MenuAccessMasterRepository;
import io.vamani.application.repository.MenuMasterRepository;
import io.vamani.application.repository.UserRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.Instant;
import java.util.*;

/**
 * REST controller for managing MenuAccessMaster.
 */
@RestController
@RequestMapping("/api")
public class MenuAccessMasterResource {

    private final Logger log = LoggerFactory.getLogger(MenuAccessMasterResource.class);

    private static final String ENTITY_NAME = "menuAccessMaster";

    private final MenuAccessMasterRepository menuAccessMasterRepository;

    @Autowired
    private MenuMasterRepository menuMasterRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeViewRepository employeeViewRepository;

    public MenuAccessMasterResource(MenuAccessMasterRepository menuAccessMasterRepository) {
        this.menuAccessMasterRepository = menuAccessMasterRepository;
    }

    /**
     * POST  /menu-access-masters : Create a new menuAccessMaster.
     *
     * @param @menuAccessMaster the menuAccessMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new menuAccessMaster, or with status 400 (Bad Request) if the menuAccessMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/menu-access-masters")
    @Timed
    public ResponseEntity<Integer> createMenuAccessMaster(@Valid @RequestBody MenuSave menuSave) throws URISyntaxException {
        log.debug("REST request to save MenuAccessMaster : {}", menuSave);
        int ctr = 0;
        menuAccessMasterRepository.deleteByAuthorityName(menuSave.getCardNo());
        for (Long menuId : menuSave.getMenus()) {
            MenuMaster menuMaster = menuMasterRepository.findById(menuId).orElse(null);
            if (menuMaster.getFolderId() != null && menuMaster.getFolderId().longValue() > 0) {
                MenuMaster menuMaster2 = menuMasterRepository.findById(menuMaster.getFolderId()).orElse(null);
                if (menuMaster2.getFolderId() != null && menuMaster2.getFolderId().longValue() > 0) {
                    MenuMaster menuMaster3 = menuMasterRepository.findById(menuMaster2.getFolderId()).orElse(null);
                    List<MenuAccessMaster> menuAccessMasters3 = menuAccessMasterRepository.findByAuthorityNameAndMenuId(menuSave.getCardNo(), menuMaster3.getId());
                    if(menuAccessMasters3 != null && menuAccessMasters3.size()>0) {} else {
                        MenuAccessMaster menuAccessMaster = new MenuAccessMaster();
                        menuAccessMaster.setAuthorityName(menuSave.getCardNo());
                        menuAccessMaster.setMenuMaster(menuMaster3);
                        menuAccessMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                        menuAccessMaster.setCreatedDate(Instant.now());
                        menuAccessMasterRepository.save(menuAccessMaster);
                        ++ctr;
                    }

                    List<MenuAccessMaster> menuAccessMasters2 = menuAccessMasterRepository.findByAuthorityNameAndMenuId(menuSave.getCardNo(), menuMaster2.getId());
                    if(menuAccessMasters2 != null && menuAccessMasters2.size()>0) {} else {
                        MenuAccessMaster menuAccessMaster = new MenuAccessMaster();
                        menuAccessMaster.setAuthorityName(menuSave.getCardNo());
                        menuAccessMaster.setMenuMaster(menuMaster2);
                        menuAccessMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                        menuAccessMaster.setCreatedDate(Instant.now());
                        menuAccessMasterRepository.save(menuAccessMaster);
                        ++ctr;
                    }

                    List<MenuAccessMaster> menuAccessMasters = menuAccessMasterRepository.findByAuthorityNameAndMenuId(menuSave.getCardNo(), menuMaster.getId());
                    if(menuAccessMasters != null && menuAccessMasters.size()>0) {} else {
                        MenuAccessMaster menuAccessMaster = new MenuAccessMaster();
                        menuAccessMaster.setAuthorityName(menuSave.getCardNo());
                        menuAccessMaster.setMenuMaster(menuMaster);
                        menuAccessMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                        menuAccessMaster.setCreatedDate(Instant.now());
                        menuAccessMasterRepository.save(menuAccessMaster);
                        ++ctr;
                    }
                } else {
                    List<MenuAccessMaster> menuAccessMasters2 = menuAccessMasterRepository.findByAuthorityNameAndMenuId(menuSave.getCardNo(), menuMaster2.getId());
                    if(menuAccessMasters2 != null && menuAccessMasters2.size()>0) {} else {
                        MenuAccessMaster menuAccessMaster = new MenuAccessMaster();
                        menuAccessMaster.setAuthorityName(menuSave.getCardNo());
                        menuAccessMaster.setMenuMaster(menuMaster2);
                        menuAccessMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                        menuAccessMaster.setCreatedDate(Instant.now());
                        menuAccessMasterRepository.save(menuAccessMaster);
                        ++ctr;
                    }

                    List<MenuAccessMaster> menuAccessMasters = menuAccessMasterRepository.findByAuthorityNameAndMenuId(menuSave.getCardNo(), menuMaster.getId());
                    if(menuAccessMasters != null && menuAccessMasters.size()>0) {} else {
                        MenuAccessMaster menuAccessMaster = new MenuAccessMaster();
                        menuAccessMaster.setAuthorityName(menuSave.getCardNo());
                        menuAccessMaster.setMenuMaster(menuMaster);
                        menuAccessMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                        menuAccessMaster.setCreatedDate(Instant.now());
                        menuAccessMasterRepository.save(menuAccessMaster);
                        ++ctr;
                    }
                }
            } else {
                List<MenuAccessMaster> menuAccessMasters = menuAccessMasterRepository.findByAuthorityNameAndMenuId(menuSave.getCardNo(), menuMaster.getId());
                if(menuAccessMasters != null && menuAccessMasters.size()>0) {} else {
                    MenuAccessMaster menuAccessMaster = new MenuAccessMaster();
                    menuAccessMaster.setAuthorityName(menuSave.getCardNo());
                    menuAccessMaster.setMenuMaster(menuMaster);
                    menuAccessMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                    menuAccessMaster.setCreatedDate(Instant.now());
                    menuAccessMasterRepository.save(menuAccessMaster);
                    ++ctr;
                }
            }
        }
        return ResponseEntity.created(new URI("/api/menu-access-masters/" + ctr))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, ctr+""))
            .body(ctr);
    }

    /**
     * PUT  /menu-access-masters : Updates an existing menuAccessMaster.
     *
     * @param menuAccessMaster the menuAccessMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated menuAccessMaster,
     * or with status 400 (Bad Request) if the menuAccessMaster is not valid,
     * or with status 500 (Internal Server Error) if the menuAccessMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/menu-access-masters")
    @Timed
    public ResponseEntity<MenuAccessMaster> updateMenuAccessMaster(@Valid @RequestBody MenuAccessMaster menuAccessMaster) throws URISyntaxException {
        log.debug("REST request to update MenuAccessMaster : {}", menuAccessMaster);
        if (menuAccessMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MenuAccessMaster result = menuAccessMasterRepository.save(menuAccessMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, menuAccessMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /menu-access-masters : get all the menuAccessMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of menuAccessMasters in body
     */
    @GetMapping("/menu-access-masters")
    @Timed
    public ResponseEntity<List<MenuAccessMaster>> getAllMenuAccessMasters(@PageableDefault(value = Integer.MAX_VALUE) Pageable pageable) {
        log.debug("REST request to get a page of MenuAccessMasters");
        Page<MenuAccessMaster> page = menuAccessMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/menu-access-masters");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /menu-access-masters/:id : get the "id" menuAccessMaster.
     *
     * @param id the id of the menuAccessMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the menuAccessMaster, or with status 404 (Not Found)
     */
    @GetMapping("/menu-access-masters/{id}")
    @Timed
    public ResponseEntity<MenuAccessMaster> getMenuAccessMaster(@PathVariable Long id) {
        log.debug("REST request to get MenuAccessMaster : {}", id);
        Optional<MenuAccessMaster> menuAccessMaster = menuAccessMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(menuAccessMaster);
    }

    /**
     * DELETE  /menu-access-masters/:id : delete the "id" menuAccessMaster.
     *
     * @param id the id of the menuAccessMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/menu-access-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteMenuAccessMaster(@PathVariable Long id) {
        log.debug("REST request to delete MenuAccessMaster : {}", id);

        menuAccessMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /menu-access-masters : get all the menuAccessMasters.
     *
     * @param @pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of menuAccessMasters in body
     */
    @GetMapping("/menu-access-masters-authority/{login}")
    @Timed
    public ResponseEntity<List<MenuDetail>> getAllMenuAccessMastersByAuthority(@PathVariable String login) {
        log.debug("REST request to get a page of MenuAccessMasters");
        Set<String> authorityNameList = new HashSet<>();
        authorityNameList.add("ROLE_USER");
        authorityNameList.add(login);
        List<MenuMaster> menuAccessMasters = menuAccessMasterRepository.findAllMenuByAuthorityName(authorityNameList);
        List<MenuDetail> menuDetails = new ArrayList<>();
        for (MenuMaster menuMaster : menuAccessMasters) {
            if(menuMaster.isParent()) {
                MenuDetail menuDetail = new MenuDetail();
                menuDetail.setId(menuMaster.getId());
                menuDetail.setCollapsed(menuMaster.isCollapsed());
                menuDetail.setIcon(menuMaster.getMenuIcon());
                menuDetail.setLabel(menuMaster.getMenuLabel());
                menuDetail.setLink(menuMaster.getMenuLink());
                menuDetail.setToolTip(menuMaster.getToolTip());
                menuDetail.setType(new ArrayList<>(authorityNameList));
                List<MenuDetail> menuDetails2 = new ArrayList<>();
                if (menuMaster.isFolder()) {
                    for (MenuMaster menuMaster2 : menuAccessMasters) {
                        if (menuMaster2.getFolderId() != null && menuMaster2.getFolderId().longValue() == menuMaster.getId().longValue()) {
                            MenuDetail menuDetail2 = new MenuDetail();
                            menuDetail2.setId(menuMaster2.getId());
                            menuDetail2.setCollapsed(menuMaster2.isCollapsed());
                            menuDetail2.setIcon(menuMaster2.getMenuIcon());
                            menuDetail2.setLabel(menuMaster2.getMenuLabel());
                            menuDetail2.setLink(menuMaster2.getMenuLink());
                            menuDetail2.setType(new ArrayList<>(authorityNameList));
                            menuDetail2.setToolTip(menuMaster2.getToolTip());
                            List<MenuDetail> menuDetails3 = new ArrayList<>();
                            if (menuMaster2.isFolder()) {
                                for (MenuMaster menuMaster3 : menuAccessMasters) {
                                    if (menuMaster3.getFolderId() != null && menuMaster3.getFolderId().longValue() == menuMaster2.getId().longValue()) {
                                        MenuDetail menuDetail3 = new MenuDetail();
                                        menuDetail3.setId(menuMaster3.getId());
                                        menuDetail3.setCollapsed(menuMaster3.isCollapsed());
                                        menuDetail3.setIcon(menuMaster3.getMenuIcon());
                                        menuDetail3.setLabel(menuMaster3.getMenuLabel());
                                        menuDetail3.setLink(menuMaster3.getMenuLink());
                                        menuDetail3.setType(new ArrayList<>(authorityNameList));
                                        menuDetail3.setToolTip(menuMaster3.getToolTip());
                                        List<MenuDetail> menuDetails4 = new ArrayList<>();
                                        if (menuMaster3.isFolder()) {
                                            for (MenuMaster menuMaster4 : menuAccessMasters) {
                                                if (menuMaster4.getFolderId() != null && menuMaster4.getFolderId().longValue() == menuMaster3.getId().longValue()) {
                                                    MenuDetail menuDetail4 = new MenuDetail();
                                                    menuDetail4.setId(menuMaster4.getId());
                                                    menuDetail4.setCollapsed(menuMaster4.isCollapsed());
                                                    menuDetail4.setIcon(menuMaster4.getMenuIcon());
                                                    menuDetail4.setLabel(menuMaster4.getMenuLabel());
                                                    menuDetail4.setLink(menuMaster4.getMenuLink());
                                                    menuDetail4.setType(new ArrayList<>(authorityNameList));
                                                    menuDetail4.setToolTip(menuMaster4.getToolTip());
                                                    menuDetails4.add(menuDetail4);
                                                }
                                            }
                                        }
                                        menuDetail3.setSubItem(menuDetails4);
                                        menuDetails3.add(menuDetail3);
                                    }
                                }
                            }
                            menuDetail2.setSubItem(menuDetails3);
                            menuDetails2.add(menuDetail2);
                        }
                    }
                }
                menuDetail.setSubItem(menuDetails2);
                menuDetails.add(menuDetail);
            }
        }
        return ResponseEntity.ok().body(menuDetails);
    }

    /**
     * GET  /menu-access-masters : get all the menuAccessMasters.
     *
     * @param @pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of menuAccessMasters in body
     */
    @PostMapping("/menu-access-masters-mobile-authority")
    @Timed
    public ResponseEntity<List<MenuDetail>> getAllMenuAccessMastersMobileByAuthority(@RequestBody MenuSearch menuSearch) {
        log.debug("REST request to get a page of MenuAccessMasters");

        EmployeeView employeeView = employeeViewRepository.findByCardNo(menuSearch.getUsername()).orElse(null);
        boolean registeredUser = true;
        /*boolean mobileValidate = false;
        String[] array = menuSearch.getSerial().split(",");
        for (String mobile : array) {
            if(mobile.equalsIgnoreCase(employeeView.getPhone())) {
                mobileValidate = true;
            }
        }
        if (mobileValidate == false) {
            HttpHeaders httpHeaders = new HttpHeaders();
            return new ResponseEntity<List<MenuDetail>>(null, httpHeaders, HttpStatus.PRECONDITION_FAILED);
        }*/
        if ((menuSearch.getImei() != null && menuSearch.getImei().length() > 0) || (menuSearch.getSerial() != null && menuSearch.getSerial().length() > 0)) {
            User user = userRepository.findOneByLogin(menuSearch.getUsername().toLowerCase()).orElse(null);
            if (user != null) {
                if ((user.getImei() != null && user.getImei().length() > 0) || (user.getSerial() != null && user.getSerial().length() > 0)) {
                    if (user.getImei() != null && menuSearch.getImei() != null && user.getImei().equalsIgnoreCase(menuSearch.getImei())) {
                    } else if (user.getSerial() != null && menuSearch.getSerial() != null && user.getSerial().equalsIgnoreCase(menuSearch.getSerial())) {
                    } else {
                        registeredUser = false;
                    }
                }
            }
        }

        if (registeredUser == false) {
            HttpHeaders httpHeaders = new HttpHeaders();
            return new ResponseEntity<List<MenuDetail>>(null, httpHeaders, HttpStatus.CONFLICT);
        }

        Set<String> authorityNameList = new HashSet<>();
        authorityNameList.add("ROLE_USER");
        authorityNameList.add(menuSearch.getUsername());
        List<MenuAccessMaster> menuAccessMasters = menuAccessMasterRepository.findAllByAuthorityNameAndMobile(authorityNameList);
        List<MenuDetail> menuDetails = new ArrayList<>();
        for (MenuAccessMaster menuAccessMaster : menuAccessMasters) {
            if(menuAccessMaster.getMenuMaster().isParent()) {
                MenuMaster menuMaster = menuAccessMaster.getMenuMaster();
                MenuDetail menuDetail = new MenuDetail();
                menuDetail.setId(menuMaster.getId());
                menuDetail.setCollapsed(menuMaster.isCollapsed());
                menuDetail.setIcon(menuMaster.getMenuIcon());
                menuDetail.setLabel(menuMaster.getMenuLabel());
                menuDetail.setLink(menuMaster.getMenuLink());
                menuDetail.setToolTip(menuMaster.getToolTip());
                menuDetail.setMobileClass(menuMaster.getMobileClass());
                menuDetail.setType(new ArrayList<>(authorityNameList));
                List<MenuDetail> menuDetails2 = new ArrayList<>();
                if (menuMaster.isFolder()) {
                    for (MenuAccessMaster menuAccessMaster2 : menuAccessMasters) {
                        if (menuAccessMaster2.getMenuMaster().getFolderId() != null && menuAccessMaster2.getMenuMaster().getFolderId().longValue() == menuMaster.getId().longValue()) {
                            MenuMaster menuMaster2 = menuAccessMaster2.getMenuMaster();
                            MenuDetail menuDetail2 = new MenuDetail();
                            menuDetail2.setId(menuMaster2.getId());
                            menuDetail2.setCollapsed(menuMaster2.isCollapsed());
                            menuDetail2.setIcon(menuMaster2.getMenuIcon());
                            menuDetail2.setLabel(menuMaster2.getMenuLabel());
                            menuDetail2.setLink(menuMaster2.getMenuLink());
                            menuDetail2.setType(new ArrayList<>(authorityNameList));
                            menuDetail2.setToolTip(menuMaster2.getToolTip());
                            menuDetail2.setMobileClass(menuMaster2.getMobileClass());
                            List<MenuDetail> menuDetails3 = new ArrayList<>();
                            if (menuMaster2.isFolder()) {
                                for (MenuAccessMaster menuAccessMaster3 : menuAccessMasters) {
                                    if (menuAccessMaster3.getMenuMaster().getFolderId() != null && menuAccessMaster3.getMenuMaster().getFolderId().longValue() == menuMaster2.getId().longValue()) {
                                        MenuMaster menuMaster3 = menuAccessMaster3.getMenuMaster();
                                        MenuDetail menuDetail3 = new MenuDetail();
                                        menuDetail3.setId(menuMaster3.getId());
                                        menuDetail3.setCollapsed(menuMaster3.isCollapsed());
                                        menuDetail3.setIcon(menuMaster3.getMenuIcon());
                                        menuDetail3.setLabel(menuMaster3.getMenuLabel());
                                        menuDetail3.setLink(menuMaster3.getMenuLink());
                                        menuDetail3.setType(new ArrayList<>(authorityNameList));
                                        menuDetail3.setToolTip(menuMaster3.getToolTip());
                                        menuDetail3.setMobileClass(menuMaster3.getMobileClass());
                                        List<MenuDetail> menuDetails4 = new ArrayList<>();
                                        if (menuMaster3.isFolder()) {
                                            for (MenuAccessMaster menuAccessMaster4 : menuAccessMasters) {
                                                if (menuAccessMaster4.getMenuMaster().getFolderId() != null && menuAccessMaster4.getMenuMaster().getFolderId().longValue() == menuMaster3.getId().longValue()) {
                                                    MenuMaster menuMaster4 = menuAccessMaster4.getMenuMaster();
                                                    MenuDetail menuDetail4 = new MenuDetail();
                                                    menuDetail4.setId(menuMaster4.getId());
                                                    menuDetail4.setCollapsed(menuMaster4.isCollapsed());
                                                    menuDetail4.setIcon(menuMaster4.getMenuIcon());
                                                    menuDetail4.setLabel(menuMaster4.getMenuLabel());
                                                    menuDetail4.setLink(menuMaster4.getMenuLink());
                                                    menuDetail4.setType(new ArrayList<>(authorityNameList));
                                                    menuDetail4.setToolTip(menuMaster4.getToolTip());
                                                    menuDetail4.setMobileClass(menuMaster4.getMobileClass());
                                                    menuDetails4.add(menuDetail4);
                                                }
                                            }
                                        }
                                        menuDetail3.setSubItem(menuDetails4);
                                        menuDetails3.add(menuDetail3);
                                    }
                                }
                            }
                            menuDetail2.setSubItem(menuDetails3);
                            menuDetails2.add(menuDetail2);
                        }
                    }
                }
                menuDetail.setSubItem(menuDetails2);
                menuDetails.add(menuDetail);
            }
        }
        return ResponseEntity.ok().body(menuDetails);
    }

    /**
     * GET  /menu-access-masters : get all the menuAccessMasters.
     *
     * @param @pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of menuAccessMasters in body
     */
    @GetMapping("/menu-access-masters-tree/{login}")
    @Timed
    public ResponseEntity<List<TreeViewItem>> getAllMenuAccessMastersByUser(@PathVariable String login) {
        log.debug("REST request to get a page of MenuAccessMasters");
        Set<String> authorityNameList = new HashSet<>();
        List<MenuAccessMaster> menuAccessMasters = menuAccessMasterRepository.findByAuthorityName(login);
        List<String> ids = new ArrayList<>();
        for (MenuAccessMaster menuAccessMaster : menuAccessMasters) {
            if(menuAccessMaster.getMenuMaster().isFolder() == false)
                ids.add(menuAccessMaster.getMenuMaster().getId().longValue() + "");
        }
        authorityNameList.add(login);
        List<MenuMaster> menuMasters = menuMasterRepository.findAllByNonDefault();
        List<TreeViewItem> treeViewItems = new ArrayList<>();
        for (MenuMaster menuMaster : menuMasters) {
            if(menuMaster.isParent()) {
                TreeViewItem treeViewItem = new TreeViewItem();
                treeViewItem.setText(menuMaster.getMenuLabel());
                treeViewItem.setValue(menuMaster.getId());
                List<TreeViewItem> children2 = new ArrayList<>();
                if (menuMaster.isFolder()) {
                    for (MenuMaster menuMaster2 : menuMasters) {
                        if (menuMaster2.getFolderId() != null && menuMaster2.getFolderId().longValue() == menuMaster.getId().longValue()) {
                            TreeViewItem treeViewItem2 = new TreeViewItem();
                            treeViewItem2.setText(menuMaster2.getMenuLabel());
                            treeViewItem2.setValue(menuMaster2.getId());
                            List<TreeViewItem> children3 = new ArrayList<>();
                            if (menuMaster2.isFolder()) {
                                for (MenuMaster menuMaster3 : menuMasters) {
                                    if (menuMaster3.getFolderId() != null && menuMaster3.getFolderId().longValue() == menuMaster2.getId().longValue()) {
                                        TreeViewItem treeViewItem3 = new TreeViewItem();
                                        treeViewItem3.setText(menuMaster3.getMenuLabel());
                                        treeViewItem3.setValue(menuMaster3.getId());
                                        if(ids.contains(menuMaster3.getId().longValue()+"")){
                                            treeViewItem3.setChecked(true);
                                        } else {
                                            treeViewItem3.setChecked(false);
                                        }
                                        children3.add(treeViewItem3);
                                    }
                                }
                            } else {
                                if(ids.contains(menuMaster2.getId().longValue()+"")){
                                    treeViewItem2.setChecked(true);
                                } else {
                                    treeViewItem2.setChecked(false);
                                }
                            }
                            treeViewItem2.setChildren(children3);
                            children2.add(treeViewItem2);
                        }
                    }
                } else {
                    if(ids.contains(menuMaster.getId().longValue()+"")){
                        treeViewItem.setChecked(true);
                    } else {
                        treeViewItem.setChecked(false);
                    }
                }
                treeViewItem.setChildren(children2);
                treeViewItems.add(treeViewItem);
            }
        }
        return ResponseEntity.ok().body(treeViewItems);
    }
}
