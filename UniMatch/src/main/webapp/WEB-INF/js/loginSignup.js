$(function() {
          $.scrollify({
              section: ".section",
          });
      });
  
      
      var modal = document.getElementById("emailModal");
      var createAccountBtn = document.getElementById("createAccountBtn");
      var loginLink = document.getElementById("loginLink");
      var span = document.getElementsByClassName("close")[0];
      var modalMessage = document.querySelector(".modal-message");
      var submitModal = document.getElementById("submitModal");
  
      
      createAccountBtn.onclick = function() {
          modal.style.display = "block";
          modalMessage.textContent = "Welcome! Please enter your details.";
          submitModal.style.display="block";
          submitModal.textContent = "Create Account";
      };
  
    
      loginLink.onclick = function() {
          modal.style.display = "block";
          modalMessage.textContent = "Welcome Back!";
          submitModal.style.display="none";
      };
function move(s)
{
	$.scrollify({
              section: "#"+String(s),
          });
}
      
      span.onclick = function() {
          modal.style.display = "none";
      };
  
      window.onclick = function(event) {
          if (event.target == modal) {
              modal.style.display = "none";
          }
      };