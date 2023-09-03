/* after changing this file run 'npm run webpack:build' */
import '../content/scss/vendor.scss';
// Imports all fontawesome core and solid icons
import { library } from '@fortawesome/fontawesome-svg-core';
import {
  faAngleDoubleLeft,
  faAngleDoubleRight,
  faArrowDown,
  faArrowLeft,
  faArrowRight,
  faArrowUp,
  faAsterisk,
  faBan,
  faBars,
  faBell,
  faBezierCurve,
  faBirthdayCake,
  faBook,
  faBriefcase,
  faBuilding,
  faCalendar,
  faCalendarAlt,
  faChartBar,
  faChartLine,
  faCheck,
  faChevronDown,
  faChevronLeft,
  faChevronRight,
  faChevronUp,
  faCircle,
  faClock,
  faCloud,
  faCodeBranch,
  faCog,
  faCommentAlt,
  faComments,
  faCopy,
  faCrosshairs,
  faDatabase,
  faDownload,
  faEdit,
  faEnvelope,
  faExchangeAlt,
  faExpandArrowsAlt,
  faEye,
  faFileAlt,
  faFileDownload,
  faFileExcel,
  faFilePdf,
  faFileUpload,
  faFilter,
  faFlag,
  faFolder,
  faGavel,
  faGift,
  faHdd,
  faHeart,
  faHome,
  faImage,
  faInbox,
  faInfo,
  faLightbulb,
  faList,
  faListAlt,
  faLock,
  faMapMarkerAlt,
  faMedkit,
  faMinus,
  faMinusCircle,
  faMinusSquare,
  faMobileAlt,
  faNewspaper,
  faPaperPlane,
  faPaperclip,
  faPause,
  faPencilAlt,
  faPhone,
  faPiggyBank,
  faPlay,
  faPlus,
  faPlusCircle,
  faPlusSquare,
  faPoll,
  faQrcode,
  faQuestionCircle,
  faQuoteLeft,
  faQuoteRight,
  faRetweet,
  faRoad,
  faRupeeSign,
  faSave,
  faSearch,
  faShieldAlt,
  faSignInAlt,
  faSignOutAlt,
  faSitemap,
  faSort,
  faSortDown,
  faSortUp,
  faSpinner,
  faStar,
  faSync,
  faSyncAlt,
  faTachometerAlt,
  faTags,
  faTasks,
  faTh,
  faThList,
  faThumbsUp,
  faTimes,
  faTrashAlt,
  faTree,
  faTrophy,
  faUnlock,
  faUser,
  faUserCheck,
  faUserCircle,
  faUserPlus,
  faUsers,
  faWonSign,
  faWrench
} from '@fortawesome/free-solid-svg-icons';

// Adds the SVG icon to the library so you can use it in your page
library.add(faAngleDoubleLeft);
library.add(faAngleDoubleRight);
library.add(faArrowDown);
library.add(faArrowLeft);
library.add(faArrowRight);
library.add(faArrowUp);
library.add(faAsterisk);
library.add(faBan);
library.add(faBars);
library.add(faBell);
library.add(faBezierCurve);
library.add(faBirthdayCake);
library.add(faBook);
library.add(faBriefcase);
library.add(faBuilding);
library.add(faCalendar);
library.add(faCalendarAlt);
library.add(faChartBar);
library.add(faChartLine);
library.add(faCheck);
library.add(faChevronDown);
library.add(faChevronLeft);
library.add(faChevronRight);
library.add(faChevronUp);
library.add(faCircle);
library.add(faClock);
library.add(faCloud);
library.add(faCodeBranch);
library.add(faCog);
library.add(faCommentAlt);
library.add(faComments);
library.add(faCopy);
library.add(faCrosshairs);
library.add(faDatabase);
library.add(faDownload);
library.add(faEdit);
library.add(faEnvelope);
library.add(faExchangeAlt);
library.add(faExpandArrowsAlt);
library.add(faEye);
library.add(faFileAlt);
library.add(faFileDownload);
library.add(faFileExcel);
library.add(faFilePdf);
library.add(faFileUpload);
library.add(faFilter);
library.add(faFlag);
library.add(faFolder);
library.add(faGavel);
library.add(faGift);
library.add(faHdd);
library.add(faHeart);
library.add(faHome);
library.add(faImage);
library.add(faInbox);
library.add(faInfo);
library.add(faLightbulb);
library.add(faList);
library.add(faListAlt);
library.add(faLock);
library.add(faMapMarkerAlt);
library.add(faMedkit);
library.add(faMinus);
library.add(faMinusCircle);
library.add(faMinusSquare);
library.add(faMobileAlt);
library.add(faNewspaper);
library.add(faPaperPlane);
library.add(faPaperclip);
library.add(faPause);
library.add(faPencilAlt);
library.add(faPhone);
library.add(faPiggyBank);
library.add(faPlay);
library.add(faPlus);
library.add(faPlusCircle);
library.add(faPlusSquare);
library.add(faPoll);
library.add(faQrcode);
library.add(faQuestionCircle);
library.add(faQuoteLeft);
library.add(faQuoteRight);
library.add(faRetweet);
library.add(faRoad);
library.add(faRupeeSign);
library.add(faSave);
library.add(faSearch);
library.add(faShieldAlt);
library.add(faSignInAlt);
library.add(faSignOutAlt);
library.add(faSitemap);
library.add(faSort);
library.add(faSortDown);
library.add(faSortUp);
library.add(faSpinner);
library.add(faStar);
library.add(faSync);
library.add(faSyncAlt);
library.add(faTachometerAlt);
library.add(faTags);
library.add(faTasks);
library.add(faTh);
library.add(faThList);
library.add(faThumbsUp);
library.add(faTimes);
library.add(faTrashAlt);
library.add(faTree);
library.add(faTrophy);
library.add(faUnlock);
library.add(faUser);
library.add(faUserCheck);
library.add(faUserCircle);
library.add(faUserPlus);
library.add(faUsers);
library.add(faWonSign);
library.add(faWrench);
