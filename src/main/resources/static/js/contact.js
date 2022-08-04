var contactForm = document.getElementById("contact_form");

window.addEventListener('load', (event) => {
    if (contactForm) {
        contactForm.addEventListener('submit', (event) => {
            event.preventDefault();

            const formData = new FormData(event.target);
            const email = formData.get('email');
            const name = formData.get('name');
            const mess = formData.get('mess');

            if (!email || !name || !mess) {
                return alert('Моля, попълни всички полета!');
            } else {
                const object = {
                    email: email,
                    name: name,
                    mess: mess
                };
                fetch("http://localhost/programming/Let'sCook/api/feedback.php", {
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
                    if (text.includes("email")) {
                        event.target.reset();
                        return alert('Невалиден имейл адрес!');
                    } else if (text.includes("name")) {
                        event.target.reset();
                        return alert('Невалидно име!');
                    } else if (text.includes("message")) {
                        event.target.reset();
                        return alert('Невалидно съобщение!');
                    } else if (text.includes("thanks")) {
                        event.target.reset();
                        return alert('Благодарим за обартната връзка. Тя е важна за нас. &#128578');
                        //redirect to home page
                        document.location.href = "./index.html";
                    }
                }).catch(function (error) {
                    alert(error);
                });
            }
        });
    }
});
