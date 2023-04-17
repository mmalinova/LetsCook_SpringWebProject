const contactForm = document.getElementById("contact_form");

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
                    mode: 'no-cors',
                    method: 'POST',
                    headers: {
                        "Content-Type": "application/json; charset=UTF-8",
                    },
                    body: JSON.stringify(object)
                }).then(function () {
                    event.target.reset();
                    alert('Благодарим за обартната връзка. Тя е важна за нас.');
                }).catch(function (error) {
                    alert(error);
                });
            }
        });
    }
});
