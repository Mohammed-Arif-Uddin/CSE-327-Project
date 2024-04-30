import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "../api/axios";
import PostCard from "../components/PostCard";

const Search = () => {
  const { searchkey } = useParams();
  const [result, setResult] = useState([]);

  useEffect(() => {
    const searchFunc = async () => {
      const fresult = await axios.get(`search/${searchkey}`);
      setResult(fresult.data);
    };
    searchFunc();
  }, [searchkey]);

  if (result.length === 0)
    return (
      <div className="flex justify-center my-12">
        <p className="my-12">No Result</p>
      </div>
    );
  else
    return (
      <div className="my-12 flex flex-wrap xl:w-[80%] xl:mx-auto">
        {result.map((post) => {
          var image = "https://www.freeiconspng.com/thumbs/no-image-icon/no-image-icon-21.png";
          if (post.img.length > 0) {
            image = post.img[0].replace("http:\\", "http:\\\\");
          }
          return (
            <PostCard
              className="m-4 xl:m-8"
              key={post._id}
              address={post.locationName}
              rent={post.rent}
              size={post.size}
              id={post._id}
              imageUrl={image}
              cid={post._creator}
            />
          );
        })}
      </div>
    );
};

export default Search;
