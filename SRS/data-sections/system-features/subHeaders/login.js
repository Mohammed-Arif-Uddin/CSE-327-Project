var login = {
  name: "Register and Login Users",
  id: "4.1",
  page: "10",
  content: `<p>Use Case:&nbsp;Register and Login using Google</p>
    <p>Actor(s):&nbsp;User</p>
    <p>Purpose:&nbsp;The user can register for this system and log in with their Google account.</p>
    <p>Type:&nbsp;(Primary)</p><br>
    <h6>Typical course of events:</h6> <br>

    <div class="grid grid-cols-2">
            <div class="w-full border-[0.5px] border-black dark:border-white px-5 py-2 text-center"><b>Actor Action</b></div>
            <div class="w-full border-[0.5px] border-black dark:border-white px-5 py-2 text-center"><b>System Response</b></div>
            <div class="w-full border-[0.5px] border-black dark:border-white px-5 py-2 text-center">
            User clicks the login from the login page.
            </div>
            <div class="w-full border-[0.5px] border-black dark:border-white px-5 py-2 text-center">
            System redirects to the google login page
            </div>
            <div class="w-full border-[0.5px] border-black dark:border-white px-5 py-2 text-center">
            System redirects to the google login page</div>
            <div class="w-full border-[0.5px] border-black dark:border-white px-5 py-2 text-center">
            System redirects to the google login page
            </div>
            <div class="w-full border-[0.5px] border-black dark:border-white px-5 py-2 text-center">
            The user chooses a google account</div>
            <div class="w-full border-[0.5px] border-black dark:border-white px-5 py-2 text-center">
            The system will respond by verifying the credentials through the Google API.
            </div>
    </div>
    <br>
    <h6>Alternative Course of Events</h6> 
    <p>If the user enter incorrect google credentials, the system will redirect to login page.
</p>
    `,
};

export default login;
