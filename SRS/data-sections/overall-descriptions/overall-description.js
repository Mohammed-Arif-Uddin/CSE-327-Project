import productPerspective from "./sub-section/product-perspective.js";
import productFunction from "./sub-section/product-function.js";
import userClasses from "./sub-section/userClasses.js";
import operatingEnvironment from "./sub-section/operatingEnvironment.js";
import constraints from "./sub-section/constraints.js";
import userDoc from "./userDoc.js";
import assumptionsAndDependencies from "./sub-section/assumptionsAndDependencies.js";

var overallDescription = {
  header: "Overall Description",
  id: "2",
  page: "",
  content: "overall description",
  subHeader: [
    productPerspective,
    productFunction,
    userClasses,
    operatingEnvironment,
    constraints,
    userDoc,
    assumptionsAndDependencies,
  ],
};
export default overallDescription;
