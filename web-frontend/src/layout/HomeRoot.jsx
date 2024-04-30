import NavigationBar from "../features/NavigationBar";
import { Outlet } from "react-router-dom";

/*
  home layout with an outlet; all pages with navbar must be routed through this
*/
const HomeRoot = () => {
  return (
    <>
      <NavigationBar />
      <Outlet />
    </>
  );
};

export default HomeRoot;
