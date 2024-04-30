import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "../api/axios";
import Card from "../components/Card";
import useAuth from "../hooks/useAuth";
import useAxiosPrivate from "../hooks/useAxiosPrivate";
import GenderPieChart from "../components/GenderPieChart";
import Heatmap from "../components/HeatMap";

const Post = () => {
  const { id } = useParams();
  const [error, setError] = useState("");
  const [isLoaded, setIsLoaded] = useState(false);
  const [result, setResult] = useState({});
  const navigate = useNavigate();
  const { auth } = useAuth();
  const axiosPrivate = useAxiosPrivate();
  const [coo, setCoo] = useState([]);
  const [v, setV] = useState(0);
  const [mv, setMV] = useState(0);
  const [fv, setFV] = useState(0);
  const [avgAge, setAvgAge] = useState(0);

  useEffect(() => {
    const fetch = async () => {
      try {
        const fresult = await axios.get(`getpost/${id}`);
        if (fresult.data._creator !== auth.userId) {
          axiosPrivate.post("/view", {
            userId: auth.userId,
            postId: id,
          });
        }
        console.log(fresult.data);
        const filteredArr = fresult.data.viewerLocations
          .filter((obj) => obj.lat !== 0 && obj.lon !== 0)
          .map(({ _id, ...rest }) => rest);
        setCoo(filteredArr);
        setV(fresult.data.numOfView);
        setMV(fresult.data.maleViews);
        setFV(fresult.data.femaleViews);
        if (fresult.data.numOfView > 0) {
          const avg = fresult.data.ageSum / fresult.data.numOfView;
          setAvgAge(avg);
        }
        setResult(fresult.data);
        setIsLoaded(true);
      } catch (error) {
        if (error.status === 404) {
          setError("Post not found!");
        } else {
          setError("Could not fetch post! An error occured.");
        }
      }
    };
    fetch();
  }, []);

  const handleChat = () => {
    setIsLoaded(false);
    document.getElementById("chatBtn").disabled = true;

    const fetchData = async () => {
      const fdata = await axiosPrivate.post("/getchat", {
        chatterId: `${result._creator}`,
      });

      const chat = fdata.data;
      console.log(chat.users[0]);
      console.log(chat.users[1]);
      var chatter = null;
      if (!chat.users[0] || !chat.users[1]) return;
      if (chat.users[0]._id === auth.userId) chatter = chat.users[1];
      if (chat.users[1]._id === auth.userId) chatter = chat.users[0];

      console.log(chatter);

      await axiosPrivate.post("/addMessage", {
        chatId: chat._id,
        text: `I am interersted in your property at the following location: ${result.locationName}`,
      });

      navigate(`/profile/chat/${chatter._id}`);
    };

    fetchData();
  };

  return (
    <div className="xl:w-[80%] mx-auto">
      {error !== "" && <p>{error}</p>}
      {!isLoaded && <p>Loading...</p>}
      {isLoaded && (
        <>
          <h1 className="mb-4">Images</h1>

          {result.img.length === 0 && (
            <div className="cardsBox overflow-x-auto flex space-x-8 py-2 text-[1.2rem]">
              <Card className="bg-slate-500 text-white text-3xl py-[5rem]">No Image!</Card>
            </div>
          )}
          <div className="cardsBox overflow-x-auto flex space-x-8 py-2 text-[1.2rem]">
            {result.img.map((image) => {
              const source = image.replace("http:\\", "http:\\\\");
              return <img src={source} key={source} className="h-[32rem]" alt="" />;
            })}
          </div>

          <Card className="text-2xl">
            <h1 className="my-12">{`Address: ${result.locationName}`}</h1>
            <h1 className="my-12">{`Monthly Rent (TK): ${result.rent}`}</h1>
            <h1 className="my-12">{`Property size (sq.ft.): ${result.size}`}</h1>
            <h1 className="my-12 mb-4">{`Description: ${
              result.descrp ? result.descrp : "No Description"
            }`}</h1>
          </Card>
          {result._creator && result._creator !== auth.userId && (
            <div className="flex justify-center mt-[5vh]">
              <button
                id="chatBtn"
                onClick={handleChat}
                className="bg-slate-500 text-white font-light px-8 py-4 rounded-lg"
              >
                Enquire
              </button>
            </div>
          )}
          {result._creator === auth.userId && (
            <Card className="mt-8 p-8 text-2xl flex flex-col justify-center items-center">
              <h1 className="mb-5 text-3xl">Stats:</h1>
              <h3>Number of views: {v}</h3>
              <h3>Average age of viewers: {avgAge}</h3>
              <GenderPieChart males={mv} females={fv} />
              <Heatmap data={coo} />
            </Card>
          )}
        </>
      )}
    </div>
  );
};

export default Post;
