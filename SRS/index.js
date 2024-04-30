import data from "./data.js";

const sideBar = document.getElementById("sideBar");
const contentContainer = document.getElementById("content-container");
const contentIndex = document.getElementById("contentIndex");
const main = document.getElementById("main");

const returnDots = () => {
  var x = "";
  for (let i = 0; i < 30; i++) {
    x += ` <span class="text-black shrink"> . </span>`;
  }
  return x;
};
//using data to load side bar, main content and content index
for (let i = 0; i < data.length; i++) {
  if (data[i].subHeader.length == 0) {
    //if there is no subsection create side bar buttons only inside summaryExpansion card
    const summaryExpansion = document.createElement("div");
    summaryExpansion.className +=
      "summaryExpansion py-2 text-sm flex flex-col rounded-xl space-y-0 bg-[#58687b] text-white";
    summaryExpansion.innerHTML = `<button value=${data[i].id} class="sideBarButton header font-bold">
        ${data[i].header}
      </button>`;
    sideBar.appendChild(summaryExpansion);

    //next create the corresponding content
    contentContainer.innerHTML += `
    <div class="contentBox p-10 container mx-auto min-w-[100%] hidden w-[70%] bg-[#3d4855] text-white rounded-3xl" id=${
      data[i].id
    }>
    ${
      data[i].id !== "home"
        ? `<div class="title">
          <span class="number hidden text-sm">${data[i].id}.</span>
          <span class="text-lg font-bold">${data[i].header}</span>
        </div>`
        : ""
    }
    ${data[i].content}
    </div>`;

    //content index counterpart will be the same as sidebar so we will clone summaryExpansion
    if (data[i].id !== "home") {
      contentIndex.innerHTML += `<a href="#${data[i].id}" value=${data[i].id} 
      class="sideBarButton noEvent header flex items-end justify-between font-bold">
        <span>
          <span class="number hidden text-sm">${data[i].id}. </span>
          ${data[i].header}
        </span>
      ${returnDots()}
      <span>${data[i].page}</span>
      </a>`;
    }
  }
  //if there are subsections, create accordion for sidebar and open accordion for content index.
  else {
    //creating details element to wrap accordion
    const detail = document.createElement("details");
    detail.setAttribute("id", data[i].id);
    detail.classList.add("detail");

    //creating summary to hold header of main section
    const summary = document.createElement("summary");
    summary.innerText = data[i].header;
    summary.className +=
      "sideBarSummary px-6 py-2 bg-[#58687b] font-bold text-white text-sm rounded-xl cursor-pointer drop-shadow-xl";
    detail.appendChild(summary);

    //creating summary expansions to hold drop down elements of accordion
    const summaryExpansion = document.createElement("div");
    summaryExpansion.className +=
      "summaryExpansion py-2 text-sm flex flex-col rounded-xl mt-2 space-y-1 bg-[#58687b] text-white";

    //creating summary expansion cards for content index. In this case, our header is part of summaryExpansion (first child).
    const contentIndexContent = document.createElement("div");
    contentIndexContent.className +=
      "summaryExpansion text-sm flex flex-col rounded-xl space-y-2 bg-[#58687b] text-white";
    contentIndexContent.innerHTML += `<a href="#${data[i].subHeader[0].id}" value=${data[i].id} 
    class="sideBarButton noEvent header flex justify-between font-bold leading-none" disabled>
      <span> 
        <span class="number hidden text-sm">${data[i].id}.</span> 
        ${data[i].header}
      </span>
      ${returnDots()}
      <span>${data[i].subHeader[0].page}</span>
    </a>`;

    //adding buttons and corresponding contents for sub sections
    for (let j = 0; j < data[i].subHeader.length; j++) {
      summaryExpansion.innerHTML += `<button value=${data[i].subHeader[j].id} class="sideBarButton">${data[i].subHeader[j].name}</button>`;
      contentIndexContent.innerHTML += `
      <a href="#${data[i].subHeader[j].id}" value=${
        data[i].subHeader[j].id
      } class="sideBarButton noEvent header ml-10 flex justify-between">
        <span>
          <span class="number hidden text-sm">${data[i].subHeader[j].id}. </span>
          ${data[i].subHeader[j].name}
        </span>
        ${returnDots()}
        <span>${data[i].subHeader[j].page}</span>
      </a>`;
      contentContainer.innerHTML += `<div class="contentBox p-10 container mx-auto min-w-[100%] hidden w-[70%] bg-[#3d4855] text-white rounded-3xl" id=${
        data[i].subHeader[j].id
      }>
      ${
        j == 0
          ? `<div class="title number hidden">
              <span class="number hidden text-sm">${data[i].id}.</span>
              <span class="number hidden text-lg font-bold">${data[i].header}</span><br><br>
            </div>`
          : ""
      }
      <div class="secondtitle">
        <span class="number hidden text-sm">${data[i].subHeader[j].id}.</span>
        <span class="text-lg font-bold">${data[i].subHeader[j].name}</span>
      </div>
      ${data[i].subHeader[j].content}
      </div>`;
    }

    //now appending the nodes we created to corresponding dom parent elements.
    detail.appendChild(summaryExpansion);
    sideBar.appendChild(detail);
    contentIndex.appendChild(contentIndexContent);
  }
}

const contentBoxes = document.getElementsByClassName("contentBox");
const summaryExpansions = document.getElementsByClassName("summaryExpansion");
const sideBarButtons = document.getElementsByClassName("sideBarButton");
const home = document.getElementById("home");
const homeClone = home.cloneNode(true);
const headers = document.getElementsByClassName("header");
const numbers = document.getElementsByClassName("number");

//---------------------------------------------Adding css and event listeners------------------------------------------------------------------------------

const showContent = (id) => {
  for (let i = 0; i < contentBoxes.length; i++) {
    contentBoxes[i].classList.add("hidden");
  }
  document.getElementById(id).classList.remove("hidden");
};

for (let i = 0; i < sideBarButtons.length; i++) {
  sideBarButtons[i].classList.add(
    "block",
    "px-5",
    "py-1",
    "hover:bg-[#bdcfdd]",
    "hover:shadow-2xl",
    "text-left",
    "hover:text-black"
  );

  if (!sideBarButtons[i].classList.contains("noEvent")) {
    sideBarButtons[i].addEventListener("click", (event) => {
      for (let j = 0; j < sideBarButtons.length; j++) {
        sideBarButtons[j].classList.remove("font-bold");
      }
      sideBarButtons[i].classList.add("font-bold");
      showContent(event.target.value);
    });
  }
}

for (let i = 0; i < headers.length; i++) {
  headers[i].classList.remove("px-16");
  headers[i].classList.add("px-5");
}

//-----------------------------------------------prepare for print-------------------------------------------------

showContent("home");

const beforePrint = () => {
  //removing home div from content box and putting it in main body before the index
  if (home.parentNode) {
    home.parentNode.removeChild(home);
  }
  main.insertBefore(homeClone, main.firstChild);

  main.classList.add("max-w-[1000px]");
  main.classList.remove("flex-row", "justify-around", "space-y-0", "bg-[#e79e6d]");
  main.classList.add("flex-col", "space-y-5");
  sideBar.classList.remove("block", "w-[20%]");
  sideBar.classList.add("hidden");
  contentIndex.classList.remove("hidden", "bg-[#3d4855]");
  contentContainer.classList.remove("w-[70%]");
  contentContainer.classList.add("w-[100%]");

  for (let i = 0; i < summaryExpansions.length; i++) {
    summaryExpansions[i].classList.remove("bg-[#58687b]", "text-white");
  }
  showContent("home");
  for (let i = 0; i < contentBoxes.length; i++) {
    contentBoxes[i].classList.remove("hidden", "w-[70%]", "bg-[#3d4855]", "text-white", "p-10");
    contentBoxes[i].classList.add("w-[100%]", "p-2");
  }

  for (let i = 0; i < sideBarButtons.length; i++) {
    sideBarButtons[i].classList.remove("hover:bg-[#bdcfdd]", "hover:text-black");
    if (!sideBarButtons[i].classList.contains("header")) {
      sideBarButtons[i].classList.remove("px-5");
      sideBarButtons[i].classList.add("px-16");
    }
  }

  for (let i = 0; i < numbers.length; i++) {
    numbers[i].classList.remove("hidden");
  }

  document.getElementById("homeDiv").classList.add("h-[1000px]", "justify-center", "text-end");
};

const afterPrint = () => {
  //putting back home in the content box
  if (homeClone.parentNode) {
    homeClone.parentNode.removeChild(homeClone);
  }
  contentContainer.insertBefore(home, contentContainer.firstChild);

  main.classList.remove("max-w-[1000px]");
  main.classList.add("flex-row", "justify-around", "space-y-0", "bg-[#e79e6d]");
  main.classList.remove("flex-col", "space-y-5");
  sideBar.classList.add("block", "w-[20%]");
  sideBar.classList.remove("hidden");
  contentIndex.classList.add("hidden", "bg-[#3d4855]");
  contentContainer.classList.add("w-[70%]");
  contentContainer.classList.remove("w-[100%]");

  for (let i = 0; i < summaryExpansions.length; i++) {
    summaryExpansions[i].classList.add("bg-[#58687b]", "text-white");
  }

  for (let i = 0; i < contentBoxes.length; i++) {
    contentBoxes[i].classList.add("hidden", "w-[70%]", "bg-[#3d4855]", "text-white", "p-10");
    contentBoxes[i].classList.remove("w-[100%]", "p-2");
  }

  for (let i = 0; i < sideBarButtons.length; i++) {
    sideBarButtons[i].classList.add("px-5", "hover:bg-[#bdcfdd]", "hover:text-black");
    sideBarButtons[i].classList.remove("px-16");
  }

  for (let i = 0; i < numbers.length; i++) {
    numbers[i].classList.add("hidden");
  }

  document.getElementById("homeDiv").classList.remove("h-[1000px]", "justify-center", "text-end");

  showContent("home");
};

window.addEventListener("beforeprint", beforePrint);

window.addEventListener("afterprint", afterPrint);

window.addEventListener("resize", () => {
  if (window.innerWidth < 1500) {
    beforePrint();
  } else {
    afterPrint();
    for (let j = 0; j < sideBarButtons.length; j++) {
      if (!sideBarButtons[i].classList.contains("noEvent")) {
        sideBarButtons[j].classList.remove("font-bold");
      }
    }
  }
});
