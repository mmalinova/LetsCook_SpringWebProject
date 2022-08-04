var loginForm = document.getElementById("login-form");
var registerForm = document.getElementById("register-form");
let h1 = Array.from(document.getElementsByTagName("h1"));

window.addEventListener('load', (event) => {
    if (loginForm) {
        loginForm.addEventListener('submit', (event) => {
            event.preventDefault();

            const formData = new FormData(event.target);
            const username = formData.get('username');
            const password = formData.get('password');

            if (!username || !password) {
                return alert('Моля, попълни всички полета!');
            } else {
                const object = {
                    name: username,
                    password: password,
                };
                fetch("http://localhost/programming/Let'sCook/api/user/login.php", {
                    method: 'POST',
                    headers: {
                        "Content-Type": "application/json; charset=UTF-8",
                    },
                    body: JSON.stringify(object)
                }).then(function (response) {
                    if (response.status !== 200) {
                        alert('Проблем с кода от заявката ' +
                            response.status);
                        return;
                    }
                    // Examine the text in the response
                    return response.text();
                }).then(function (text) {
                    alert(text);
                    if (text.includes("успешно")) {
                        event.target.reset();
                        // save to sessionStorage
                        sessionStorage.setItem("username", username);
                        //redirect to home page
                        document.location.href = "./index.html";
                    }
                }).catch(function (error) {
                    alert(error);
                });
            }
        });
    }
    if (registerForm) {
        registerForm.addEventListener('submit', (event) => {
            event.preventDefault();

            const formData = new FormData(event.target);
            const username = formData.get('username');
            const email = formData.get('email');
            const password = formData.get('password');
            const repeatPass = formData.get('repeatPass');
            let is_admin = 0;

            if (!username || !email || !password || !repeatPass) {
                return alert('Всички полета са задължителни!');
            }
            const validateEmail = (email) => {
                return email.match(
                    /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/
                    ///^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
                );
            };
            if (!validateEmail(email)) {
                return alert('Невалиден имейл!');
            }
            if (password.length < 3) {
                return alert('Паролата трябва да съдържа поне три символа!');
            }
            if (password != repeatPass) {
                return alert('Паролите не съвпадат!');
            }
            if (email == "malinova29@gmail.com" || email == "mihaela__eli@abv.bg" || email == "mmalinova@dasgotvim.com") {
                is_admin = 1;
            }
            const object = {
                name: username,
                email: email,
                password: password,
                is_admin: is_admin
            };
            fetch("http://localhost/programming/Let'sCook/api/user/create.php", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json; charset=UTF-8",
                },
                body: JSON.stringify(object)
            }).then(function (response) {
                if (response.status !== 200) {
                    alert('Проблем с кода от заявката ' +
                        response.status);
                    return;
                }
                // Examine the text in the response
                return response.text();
            }).then(function (text) {
                alert(text);
                if (text.includes("успешно")) {
                    event.target.reset();
                    // save to sessionStorage
                    sessionStorage.setItem("username", username);
                    sessionStorage.setItem('email', email);
                    //redirect to home page
                    document.location.href = "./index.html";
                }
            }).catch(function (error) {
                alert(error);
            });
        });
    }
});
