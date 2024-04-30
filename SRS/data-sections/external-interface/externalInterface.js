import userInterface from "./subHeaders/userInterface.js";
import hardwareInterface from "./subHeaders/hardwareInterface.js";
import softwareInterface from "./subHeaders/softwareInterface.js";
import communicationInterface from "./subHeaders/communicationInterface.js";

var externalInterface = {
  header: "External Interface Requirements",
  id: "3",
  page: "6",
  content: "",
  subHeader: [userInterface, hardwareInterface, softwareInterface, communicationInterface],
};

export default externalInterface;
