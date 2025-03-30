console.log("Base Script Loaded")
const themeButton = document.getElementById("theme-button")
const themeText = document.getElementById("theme-text")


let appTheme = getTheme()
setTheme(appTheme)


themeButton.addEventListener("click",()=>{
    console.log("App Theme Changed")
    appTheme === "light" ? setTheme("dark") : setTheme("light")
})

function getTheme () {
    const currentTheme = localStorage.getItem("theme")
    return currentTheme ? currentTheme : "light"
}

function storeTheme (theme) {
    localStorage.setItem("theme",theme)
}

function setTheme (theme) {
    storeTheme(theme)
    if(appTheme !== "")
    document.querySelector("html").classList.remove(appTheme)
    document.querySelector("html").classList.add(theme)
    appTheme = theme
    themeText.innerText = appTheme === "light" ? "Dark" : "Light"
}
