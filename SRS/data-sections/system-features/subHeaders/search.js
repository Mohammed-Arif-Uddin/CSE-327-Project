var search = {
  name: "Search System",
  id: "4.6",
  page: "13",
  content: `
    <p>Use Case: Search System (Voice Search and Text Search)</p>
    <p>Actor(s): User</p>
    <p>Purpose:  The user can search with voice or typing.</p>
    <p>Type: Primary</p><br>
    <h6>Typical course of events:</h6> <br>

    <div class="grid grid-cols-2">
            <div class="w-full border-[0.5px] border-black dark:border-white px-5 py-2 text-center"><b>Actor Action</b></div>
            <div class="w-full border-[0.5px] border-black dark:border-white px-5 py-2 text-center"><b>System Response</b></div>

            <div class="w-full border-[0.5px] border-black dark:border-white px-5 py-2 text-center">
            The user press search with voice
            </div>
            <div class="w-full border-[0.5px] border-black dark:border-white px-5 py-2 text-center">
            The system converts the voice into text.
            </div>

            <div class="w-full border-[0.5px] border-black dark:border-white px-5 py-2 text-center">
           The user press search with typing
            </div>
            <div class="w-full border-[0.5px] border-black dark:border-white px-5 py-2 text-center">
            The system takes the text and searches the item.
            </div>
    </div>
    `,
};

export default search;
