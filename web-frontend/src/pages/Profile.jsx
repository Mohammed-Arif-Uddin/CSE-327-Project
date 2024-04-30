/* 
  Page to show users ad posts with
  Saved preferences
  Shortlisted rents
  # chatbox !! maybe not sure
*/
import React from "react";
import MyPost from "../features/MyPost";
import NavButton from "../components/NavButton";
import EditableTextField from "../components/EditableTextField";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faFileCirclePlus } from "@fortawesome/free-solid-svg-icons";
import MyChats from "../features/MyChats";
import VoiceRecorder from "../components/VoiceRecorder";

const Profile = () => {
  return (
    <div className="xl:w-[80%] mx-auto">
      <span className="ml-10 mr-10 mb-5 space-x-8 flex items-center justify-between">
        <span>My Posts</span>
        <NavButton className="flex space-x-2" to="new-post">
          <FontAwesomeIcon icon={faFileCirclePlus} />
          <h3 className="text-2xl align-end ">New Post</h3>
        </NavButton>
      </span>
      <MyPost />
      <div className="mt-16">
        <span className="ml-10 mr-10 mb-5 space-x-8 flex items-center justify-between">
          My Chats
        </span>
        <MyChats />
      </div>
      <EditableTextField className="mt-32 text-[2rem]" initialValue="hello" />
      <VoiceRecorder />
    </div>
  );
};

export default Profile;
