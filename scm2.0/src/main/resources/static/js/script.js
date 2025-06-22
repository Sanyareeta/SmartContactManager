console.log("script loaded");

document.addEventListener("DOMContentLoaded", () => {
  let currentTheme = getTheme();
  changeTheme(currentTheme);

  function changeTheme(theme) {
    document.querySelector("html").classList.add(theme);

    const changeThemeButton = document.querySelector("#theme_change_button");
    if (changeThemeButton) {
      changeThemeButton.addEventListener("click", (event) => {
        console.log("change theme button clicked");
         const oldTheme=currentTheme;
        if(currentTheme=="dark"){
        currentTheme="light";
        }
        else{
        currentTheme="dark";
        }
       console.log(currentTheme);
        changeThemeButton.querySelector('span').textContent=currentTheme;
      });
    } else {
      console.error("Theme change button not found");
    }
  }

  function setTheme(theme) {
    localStorage.setItem("theme", theme);
  }

  function getTheme() {
    let theme = localStorage.getItem("theme");
    return theme !== null ? theme : "dark";
  }
});
