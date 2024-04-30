var call = {
  name: "Call System",
  id: "4.4",
  page: "12",
  content: `
    <p>Use Case: Call system</p>
    <p>Actor(s): Evaluator</p>
    <p>Purpose:  Communication between tenant and owner via call.</p>
    <p>Type: Primary</p><br>
    <h6>Typical course of events:</h6> <br>

    <div class="grid grid-cols-2">
            <div class="w-full border-[0.5px] border-black dark:border-white px-5 py-2 text-center"><b>Actor Action</b></div>
            <div class="w-full border-[0.5px] border-black dark:border-white px-5 py-2 text-center"><b>System Response</b></div>

            <div class="w-full border-[0.5px] border-black dark:border-white px-5 py-2 text-center">
            User press the “Call Button”
            </div>
            <div class="w-full border-[0.5px] border-black dark:border-white px-5 py-2 text-center">
            The system sends the call straight to the home's owner.
            </div>

            <div class="w-full border-[0.5px] border-black dark:border-white px-5 py-2 text-center">
            User press the “Voice Message” button
            </div>
            <div class="w-full border-[0.5px] border-black dark:border-white px-5 py-2 text-center">
            The voice message will be delivered directly to the house owner by the system.
            </div>
    </div>
    `,
};

export default call;
