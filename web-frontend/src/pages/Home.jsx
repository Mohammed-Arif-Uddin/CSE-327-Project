/*
  Initial page of website
*/
import React from "react";

const BLOB_SHAPE = {
  borderRadius: "70% 30% 30% 70% / 60% 40% 60% 40%",
  transformOrigin: "50%, 50%",
  zIndex: "-1",
};

const Home = () => {
  return (
    <div className="flex flex-col lg:flex-row place-content-center justify-center">
      <div
        style={BLOB_SHAPE}
        className="animate-wiggle h-[48rem] w-[48rem] bg-cover bg-center bg-no-repeat bg-[url(..\src\assets\home-house-1.jpg)] place-self-center"
      ></div>
      <div className="lg:w-1/3">
        <h1 className="about font-mono p-16 font-black text-6xl ">
          The Smart Realtor You Always Wanted ...
        </h1>
        <p className="text-[1.6rem] px-16">
          Finding tenants or rents is no piece of cake. But It can be BETTER! Join today to find out
          yourself.
        </p>
      </div>
    </div>
  );
};

export default Home;
