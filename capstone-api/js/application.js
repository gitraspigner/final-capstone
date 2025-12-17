window.userService = new UserService();
window.productService = new ProductService();
window.categoryService = new CategoryService();
window.profileService = new ProfileService();
window.cartService = new ShoppingCartService();
function showLoginForm()
{
    templateBuilder.build('login-form', {}, 'login');
}
function showRegisterForm()
{
    templateBuilder.build('register-form', {}, 'register');
}
function hideModalForm()
{
    templateBuilder.clear('login');
    templateBuilder.clear('register');
}
function login()
{
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    userService.login(username, password);
    hideModalForm();
}
function register()
{
    const username = document.getElementById("reg-username").value;
    const password = document.getElementById("reg-password").value;
    const confirmPassword = document.getElementById("reg-confirm-password").value;
    const role = document.getElementById("reg-role").value || "USER"; //default to user if no role
    if (password !== confirmPassword)
    {
        alert("Passwords do not match.");
        return;
    }
    userService.register(username, password, confirmPassword, role)
        .then(user => {
            userService.saveUser(user);
            hideModalForm();
        })
        .catch(error => {
            console.error(error);
            const data = { error: "User registration failed." };
            templateBuilder.append("error", data, "errors");
        });
}
function showImageDetailForm(product, imageUrl)
{
    const imageDetail = {
        name: product,
        imageUrl: imageUrl
    };
    templateBuilder.build('image-detail', imageDetail, 'login');
}
function loadHome()
{
    templateBuilder.build('home', {}, 'main');
    productService.search();
    categoryService.getAllCategories(loadCategories);
}
function editProfile()
{
    profileService.loadProfile();
}
function saveProfile()
{
    const firstName = document.getElementById("firstName").value;
    const lastName = document.getElementById("lastName").value;
    const phone = document.getElementById("phone").value;
    const email = document.getElementById("email").value;
    const address = document.getElementById("address").value;
    const city = document.getElementById("city").value;
    const state = document.getElementById("state").value;
    const zip = document.getElementById("zip").value;
    const profile = {
        firstName,
        lastName,
        phone,
        email,
        address,
        city,
        state,
        zip
    };
    profileService.updateProfile(profile);
}
function showCart()
{
    cartService.loadCartPage();
}
function clearCart()
{
    cartService.clearCart();
    cartService.loadCartPage();
}
function setCategory(control)
{
    productService.addCategoryFilter(control.value);
    productService.search();
}
function setSubcategory(control)
{
    productService.addSubcategoryFilter(control.value);
    productService.search();
}
function setMinPrice(control)
{
    const label = document.getElementById("min-price-display");
    label.innerText = control.value;
    const value = control.value != 0 ? control.value : -1;
    productService.addMinPriceFilter(value);
    productService.search();
}
function setMaxPrice(control)
{
    const label = document.getElementById("max-price-display");
    label.innerText = control.value;
    const value = control.value != 500 ? control.value : -1;
    productService.addMaxPriceFilter(value);
    productService.search();
}
function closeError(control)
{
    setTimeout(() =>
    {
        control.click();
    }, 3000);
}
document.addEventListener('DOMContentLoaded', () =>
{
    loadHome();
});
document.addEventListener("DOMContentLoaded", () =>
{
    const loginSection = document.getElementById("login");
    if (!loginSection) return;
    const loginButton = loginSection.querySelector("button");
    if (!loginButton) return;
    const registerButton = loginButton.cloneNode(true);
    registerButton.textContent = "Register";
    registerButton.onclick = () =>
    {
        if (typeof showRegisterForm === "function")
        {
            showRegisterForm();
        }
    };
    loginButton.insertAdjacentElement("afterend", registerButton);
});
