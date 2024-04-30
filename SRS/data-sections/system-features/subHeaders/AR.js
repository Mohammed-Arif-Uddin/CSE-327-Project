var AR = {
  name: "Show rents in AR",
  id: "4.8",
  page: "14",
  content: `
    <p>Use Case: Mobile shows the house information</p>
    <p>Actor(s): User</p>
    <p>Purpose: When the user opens the camera via this system, all the rental information will be visible at that range of the mobile capture.
</p>
    <p>Type: Primary</p><br>
    <h6>Typical course of events:</h6> <br>

    <div class="grid grid-cols-2">
            <div class="w-full border-[0.5px] border-black dark:border-white px-5 py-2 text-center"><b>Actor Action</b></div>
            <div class="w-full border-[0.5px] border-black dark:border-white px-5 py-2 text-center"><b>System Response</b></div>

            <div class="w-full border-[0.5px] border-black dark:border-white px-5 py-2 text-center">
           The user will go in front of the building and pick up the mobile.
            </div>
            <div class="w-full border-[0.5px] border-black dark:border-white px-5 py-2 text-center">
            The system shows which buildings are rented and also shows all rented information that is captured by the user's mobile device.
            </div>
    </div>
    `,
};

export default AR;
