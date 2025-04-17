    function toggleDropdown() {
      const menu = document.getElementById("dropdownMenu");
      menu.style.display = menu.style.display === "block" ? "none" : "block";
    }

    window.addEventListener('click', function(e) {
      const trigger = document.querySelector(".user-name");
      const menu = document.getElementById("dropdownMenu");

      if (!trigger.contains(e.target)) {
        menu.style.display = "none";
      }
    });
