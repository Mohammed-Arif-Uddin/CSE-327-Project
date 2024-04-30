import { Outlet } from "react-router-dom";
import Searchbox from "../features/Searchbox";
import { useNavigate } from "react-router-dom";
const SearchRoot = () => {
  const navigate = useNavigate();
  const getSearch = (searchString) => {
    navigate(`${searchString}`);
  };
  return (
    <div>
      <Searchbox getSearch={getSearch} />
      <Outlet />
    </div>
  );
};

export default SearchRoot;
