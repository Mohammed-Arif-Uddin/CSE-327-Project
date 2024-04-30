import login from "./subHeaders/login.js";
import advertisement from "./subHeaders/advertisement.js";
import viewersInfo from "./subHeaders/viewersInfo.js";
import call from "./subHeaders/callSystem.js";
import storeSearch from "./subHeaders/storeSearch.js";
import search from "./subHeaders/search.js";
import mapping from "./subHeaders/mapping.js";
import AR from "./subHeaders/AR.js";

var systemFeatures = {
  header: "System Features",
  id: "4",
  page: "8",
  content: "",
  subHeader: [login, advertisement, viewersInfo, call, storeSearch, search, mapping, AR],
};
export default systemFeatures;
