var index = 0, slideIndex = 1;
var slides = document.getElementsByClassName("mySlides");
var dots = document.getElementsByClassName("dot");
var dashboard = document.getElementById('recipe-dashboard');
var searchVal = document.querySelector('#searchVal');
var subcat = Array.from(document.getElementsByClassName('search-by-subcat'));
var cat = Array.from(document.getElementsByClassName('cat'));
var recipeDash = document.getElementById('recipe-dash');

window.addEventListener('load', (event) => {
    setUserNav();
    if (slides.length > 0) {
        autoShowSlides();
    }
    loadRecipes();

    // Next/previous controls
    if (slides.length > 0) {
        Array.from(document.getElementsByClassName("prev"))[0].addEventListener('click', (event) => {
            console.log('prev')
            showSlides(slideIndex += -1);
        });
        Array.from(document.getElementsByClassName("next"))[0].addEventListener('click', (event) => {
            console.log('next')
            showSlides(slideIndex += 1);
        });
        let dotsArray = Array.from(document.getElementsByClassName("dot"));
        if (dotsArray.length > 0) {
            dotsArray[0].addEventListener('click', (event) => showSlides(slideIndex = 1));
            dotsArray[1].addEventListener('click', (event) => showSlides(slideIndex = 2));
            dotsArray[2].addEventListener('click', (event) => showSlides(slideIndex = 3));
            dotsArray[3].addEventListener('click', (event) => showSlides(slideIndex = 4));
            dotsArray[4].addEventListener('click', (event) => showSlides(slideIndex = 5));
        }
    }

    if (dashboard) {
        let title = localStorage.getItem("title");
        let con = localStorage.getItem("content");
        document.getElementById('cat-title').innerHTML = title;
        dashboard.innerHTML = con;
        var recipes = Array.from(document.getElementsByClassName('food-item lorem-type'));
        for (var i = 0; i < recipes.length; i++) {
            recipes[i].addEventListener('click', (event) => {
                displaySingleRecipe(event.target.id);
            })
        }
    }
    if (recipeDash) {
        let recipeCon = localStorage.getItem("recipeCon");
        let photoCon = localStorage.getItem("photoCon");
        let steps = localStorage.getItem("steps");
        let ingCon = localStorage.getItem("ingCon");
        const result = steps.split(/\r?\n/);
        document.getElementById('img-slide').innerHTML = photoCon;
        document.getElementById('recipe-info').innerHTML = recipeCon;
        document.getElementById('1st-step').innerHTML = result[0];
        document.getElementById('2nd-step').innerHTML = result[1];
        document.getElementById('3rd-step').innerHTML = result[2];
        document.getElementById('ingredients').innerHTML += ingCon;
    }

    document.getElementById('logoutBtn').addEventListener('click', async () => {
        sessionStorage.removeItem('email');
        sessionStorage.removeItem('username');
        setUserNav();
        document.location.href = './index.html';
    });

    let element = document.getElementById('Подходящи рецепти');
    if (element) {
        element.addEventListener('submit', (event) => {
            event.preventDefault();

            const formData = new FormData(event.target);
            const prodName = formData.get('productName');
            const prodQuan = formData.get('productQuan');
            const productUnit = formData.get('productUnit');

            if (!prodName || !prodQuan || !productUnit) {
                return alert('Всички полета са задължителни!');
            }
            fetch("http://localhost/programming/Let'sCook/api/product/read_by_quantity.php?name=" + prodName + "&measure_unit=" + productUnit + "&quantity=" + prodQuan, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json; charset=UTF-8",
                }
            }).then(function (response) {
                if (response.status !== 200) {
                    alert('Проблем с кода от заявката ' +
                        response.status);
                    return;
                }
                // Examine the text in the response
                return response.text();
            }).then(function (text) {
                let content = '';
                var jsonData = JSON.parse(text);
                if (jsonData.message) {
                    alert("Не са намерени подходящи рецепти!");
                }
                for (var i = 0; i < jsonData.length; i++) {
                    var recipe = jsonData[i];
                    let name = recipe.name;
                    let hours = recipe.hours;
                    let mins = recipe.minutes;
                    let recipe_id = recipe.recipe_id;
                    fetch("http://localhost/programming/Let'sCook/api/photo/read.php?recipe_id=" + recipe_id, {
                        method: 'GET',
                        headers: {
                            "Content-Type": "application/json; charset=UTF-8",
                        }
                    }).then(function (response) {
                        if (response.status !== 200) {
                            alert('Проблем с кода от заявката ' +
                                response.status);
                            return;
                        }
                        // Examine the text in the response
                        return response.text();
                    }).then(function (text) {
                        document.location.href = "./recipes_dashboard.html";
                        var jsonData = JSON.parse(text);
                        var photo = jsonData[0];
                        let img = photo.img_dir;
                        content += '<div id="' + recipe_id + '" class="food-item lorem-type"> <div id="' + recipe_id + '" class="item-wrap bottom-up play-on-scroll"> <div id="' + recipe_id + '" class="item-img"> <div id="' + recipe_id + '" class="img-holder bg-img" style="background-image: url(' + img + ');"> </div> </div> <div id="' + recipe_id + '" class="item-info"> <div id="' + recipe_id + '" > <h3 id="' + recipe_id + '" > ' + name + '</h3> <span id="' + recipe_id + '" > &#128337; ' + hours + ':' + mins + ' ч. </span> </div> <div id="' + recipe_id + '" class="cart-btn"> <i id="' + recipe_id + '" class="bx bx-cart-alt"></i> </div> </div> </div> </div>';
                        localStorage.setItem("title", event.target.id);
                        localStorage.setItem("content", content);
                    }).catch(function (error) {
                        alert(error);
                    });
                }
            }).catch(function (error) {
                alert(error);
            });
            event.target.reset();
        });
    }

    for (var i = 0; i < subcat.length; i++) {
        subcat[i].addEventListener('click', (event) => {
            displayRecipes(event);
        })
    }
    for (var i = 0; i < cat.length; i++) {
        cat[i].addEventListener('click', (event) => {
            displayRecipes(event);
        })
    }

    if (searchVal) {
        searchVal.addEventListener('keypress', function (e) {
            let searchName = document.getElementById('searchVal');
            let value = '';
            if (e.key === 'Enter') {
                fetch("http://localhost/programming/Let'sCook/api/recipe/read_by_name.php?name=" + searchName.value, {
                    method: 'GET',
                    headers: {
                        "Content-Type": "application/json; charset=UTF-8",
                    }
                }).then(function (response) {
                    if (response.status !== 200) {
                        alert('Проблем с кода от заявката ' +
                            response.status);
                        return;
                    }
                    // Examine the text in the response
                    return response.text();
                }).then(function (text) {
                    let content = '';
                    var jsonData = JSON.parse(text);
                    if (jsonData.message) {
                        alert("Не са намерени подходящи рецепти!");
                    }
                    for (var i = 0; i < jsonData.length; i++) {
                        var recipe = jsonData[i];
                        let name = recipe.name;
                        let hours = recipe.hours;
                        let mins = recipe.minutes;
                        let recipe_id = recipe.recipe_id;
                        fetch("http://localhost/programming/Let'sCook/api/photo/read.php?recipe_id=" + recipe_id, {
                            method: 'GET',
                            headers: {
                                "Content-Type": "application/json; charset=UTF-8",
                            }
                        }).then(function (response) {
                            if (response.status !== 200) {
                                alert('Проблем с кода от заявката ' +
                                    response.status);
                                return;
                            }
                            // Examine the text in the response
                            return response.text();
                        }).then(function (text) {
                            document.location.href = "./recipes_dashboard.html";
                            var jsonData = JSON.parse(text);
                            var photo = jsonData[0];
                            let img = photo.img_dir;
                            content += '<div id="' + recipe_id + '" class="food-item lorem-type"> <div id="' + recipe_id + '" class="item-wrap bottom-up play-on-scroll"> <div id="' + recipe_id + '" class="item-img"> <div id="' + recipe_id + '" class="img-holder bg-img" style="background-image: url(' + img + ');"> </div> </div> <div id="' + recipe_id + '" class="item-info"> <div id="' + recipe_id + '" > <h3 id="' + recipe_id + '" > ' + name + '</h3> <span id="' + recipe_id + '" > &#128337; ' + hours + ':' + mins + ' ч. </span> </div> <div id="' + recipe_id + '" class="cart-btn"> <i id="' + recipe_id + '" class="bx bx-cart-alt"></i> </div> </div> </div> </div>';
                            localStorage.setItem("title", "Рецепти, съдържащи името \"" + value + "\"");
                            localStorage.setItem("content", content);
                        }).catch(function (error) {
                            alert(error);
                        });
                    }
                }).catch(function (error) {
                    alert(error);
                });
                value = searchName.value;
                searchName.value = '';
            }
        });
    }

    // Upload file
    /*document.getElementById('btn_uploadfile').addEventListener('click', (event) => {
        var files = document.getElementById("file").files;

        if (files.length > 0) {
            var formData = new FormData();
            formData.append("file", files[0]);

            var xhttp = new XMLHttpRequest();

            // Set POST method and ajax file path
            xhttp.open("POST", "src/upload.php", true);

            // call on request changes state
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    var response = this.responseText;
                    alert(response);
                }
            };

            // Send request with data
            xhttp.send(formData);

        } else {
            alert('Моля, избери снимка!');
        }
    });*/
});

function displayRecipes(event) {
    let cat = event.target.name;
    let url = '';
    if (cat == undefined) {
        cat = event.target.childNodes[0].name;
    }
    if (cat == '0' || cat == '1') {
        url = "http://localhost/programming/Let'sCook/api/recipe/read_by_vegetarian.php?vegetarian=" + cat + "&category=вечеря";
    } else if (cat == 'десерт') {
        url = "http://localhost/programming/Let'sCook/api/recipe/read_by_category.php?category=" + cat;
    } else {
        url = "http://localhost/programming/Let'sCook/api/recipe/read_by_subcategory.php?subcat=" + cat;
    }
    fetch(url, {
        method: 'GET',
        headers: {
            "Content-Type": "application/json; charset=UTF-8",
        }
    }).then(function (response) {
        if (response.status !== 200) {
            alert('Проблем с кода от заявката ' +
                response.status);
            return;
        }
        // Examine the text in the response
        return response.text();
    }).then(function (text) {
        let content = '';
        var jsonData = JSON.parse(text);
        for (var i = 0; i < jsonData.length; i++) {
            var recipe = jsonData[i];
            let name = recipe.name;
            let hours = recipe.hours;
            let mins = recipe.minutes;
            let recipe_id = recipe.recipe_id;
            fetch("http://localhost/programming/Let'sCook/api/photo/read.php?recipe_id=" + recipe_id, {
                method: 'GET',
                headers: {
                    "Content-Type": "application/json; charset=UTF-8",
                }
            }).then(function (response) {
                if (response.status !== 200) {
                    alert('Проблем с кода от заявката ' +
                        response.status);
                    return;
                }
                // Examine the text in the response
                return response.text();
            }).then(function (text) {
                document.location.href = "./recipes_dashboard.html";
                var jsonData = JSON.parse(text);
                var photo = jsonData[0];
                let img = photo.img_dir;
                content += '<div id="' + recipe_id + '" class="food-item lorem-type"> <div id="' + recipe_id + '" class="item-wrap bottom-up play-on-scroll"> <div id="' + recipe_id + '" class="item-img"> <div id="' + recipe_id + '" class="img-holder bg-img" style="background-image: url(' + img + ');"> </div> </div> <div id="' + recipe_id + '" class="item-info"> <div id="' + recipe_id + '" > <h3 id="' + recipe_id + '" > ' + name + '</h3> <span id="' + recipe_id + '" > &#128337; ' + hours + ':' + mins + ' ч. </span> </div> <div id="' + recipe_id + '" class="cart-btn"> <i id="' + recipe_id + '" class="bx bx-cart-alt"></i> </div> </div> </div> </div>';
                if (event.target.id) {
                    localStorage.setItem("title", event.target.id);
                } else {
                    localStorage.setItem("title", event.target.innerHTML);
                }
                localStorage.setItem("content", content);
            }).catch(function (error) {
                alert(error);
            });
        }
    }).catch(function (error) {
        alert(error);
    });
}

function displaySingleRecipe(id) {
    fetch("http://localhost/programming/Let'sCook/api/recipe/read_single.php?recipe_id=" + id, {
        method: 'GET',
        headers: {
            "Content-Type": "application/json; charset=UTF-8",
        }
    }).then(function (response) {
        if (response.status !== 200) {
            alert('Проблем с кода от заявката ' +
                response.status);
            return;
        }
        // Examine the text in the response
        return response.text();
    }).then(function (text) {
        var content = '';
        var recipe = JSON.parse(text);
        var category = recipe.category.charAt(0).toUpperCase() + recipe.category.slice(1);
        content = '<h2>' + recipe.name + '</h2> <div class="recipe-icons"> <article> <h5>Категория</h5> <p> &#127869;&#65039; ' + category + '</p> </article> <article> <h5>Вегетарианско</h5> <p> &#129367; ';
        if (recipe.vegetarian > 0) {
            content += 'Да';
        } else {
            content += 'Не';
        }
        content += '</p> </article> <article> <h5>Време за приготвяне</h5> <p> &#128337; ' + recipe.hours + ':' + recipe.minutes + ' ч.</p> </article> <article> <h5>Порции</h5> <p> &#127860; ' + recipe.portions + '</p> </article> </div>';
        localStorage.setItem("recipeCon", content);
        localStorage.setItem("steps", recipe.steps);
        fetch("http://localhost/programming/Let'sCook/api/photo/read.php?recipe_id=" + id, {
            method: 'GET',
            headers: {
                "Content-Type": "application/json; charset=UTF-8",
            }
        }).then(function (response) {
            if (response.status !== 200) {
                alert('Проблем с кода от заявката ' +
                    response.status);
                return;
            }
            // Examine the text in the response
            return response.text();
        }).then(function (text) {
            var jsonData = JSON.parse(text);
            var photoCon = '';
            for (var i = 0; i < jsonData.length; i++) {
                var photo = jsonData[i];
                photoCon += '<div class="mySlides"';
                if (i != 0) {
                    photoCon += 'style="display: none;"';
                }
                photoCon += '> <div class="img recipe-hero-img"> </div> <img src="' + photo.img_dir + '" style="width:100%"> </div>';
            }
            photoCon += '<a class="prev">&#10094;</a> <a class="next">&#10095;</a>';
            localStorage.setItem("photoCon", photoCon);
            fetch("http://localhost/programming/Let'sCook/api/product/read.php?owner_id=" + id, {
                method: 'GET',
                headers: {
                    "Content-Type": "application/json; charset=UTF-8",
                }
            }).then(function (response) {
                if (response.status !== 200) {
                    alert('Проблем с кода от заявката ' +
                        response.status);
                    return;
                }
                // Examine the text in the response
                return response.text();
            }).then(function (text) {
                document.location.href = "./recipe.html";
                var jsonData = JSON.parse(text);
                var ingCon = '';
                for (var i = 0; i < jsonData.length; i++) {
                    var ing = jsonData[i];
                    ingCon += '<p class="single-ingredient"> &#10004;&#65039; ' + ing.name + '\t\t' + ing.quantity + '\t\t' + ing.measure_unit + '</p>';
                }
                localStorage.setItem("ingCon", ingCon);
            }).catch(function (error) {
                alert(error);
            });
        }).catch(function (error) {
            alert(error);
        });
    }).catch(function (error) {
        alert(error);
    });
}

function loadRecipes() {
    let ul = document.getElementById('last-added-recipes');
    fetch("http://localhost/programming/Let'sCook/api/recipe/read.php", {
        method: 'GET',
        headers: {
            "Content-Type": "application/json; charset=UTF-8",
        }
    }).then(function (response) {
        if (response.status !== 200) {
            alert('Проблем с кода от заявката ' +
                response.status);
            return;
        }
        // Examine the text in the response
        return response.text();
    }).then(function (text) {
        let content = '';
        //console.log(text);
        var jsonData = JSON.parse(text);
        for (var i = 0; i < 6; i++) {
            var recipe = jsonData[i];
            let name = recipe.name;
            let recipe_id = recipe.recipe_id;
            fetch("http://localhost/programming/Let'sCook/api/photo/read.php?recipe_id=" + recipe_id, {
                method: 'GET',
                headers: {
                    "Content-Type": "application/json; charset=UTF-8",
                }
            }).then(function (response) {
                if (response.status !== 200) {
                    alert('Проблем с кода от заявката ' +
                        response.status);
                    return;
                }
                // Examine the text in the response
                return response.text();
            }).then(function (text) {
                var jsonData = JSON.parse(text);
                var photo = jsonData[0];
                let img = photo.img_dir;
                content += '<a href="javascript:void(0)" class="cat" name="catt" id=' + recipe_id + '> <img src="' + img + '" alt="recipeImg" /> <span class="catHover"></span><span class="iconHold">' + name + '</span> </a>';
                if (ul) {
                    ul.innerHTML = content;
                }
            }).then(function (text) {
                var catt = Array.from(document.getElementsByName('catt'));
                for (var i = 0; i < catt.length; i++) {
                    catt[i].addEventListener('click', (event) => {
                        displaySingleRecipe(event.target.parentElement.id);
                    })
                }
            }).catch(function (error) {
                alert(error);
            });
        }
    }).catch(function (error) {
        alert(error);
    });
}

function loadTable() {
    const xhttp = new XMLHttpRequest();
    let content = '';
    xhttp.onload = function () {
        const xmlTree = this.responseXML.documentElement;
        var rowData = xmlTree.getElementsByTagName('CD');
        content = '<br><table><tr><th>TITLE</th><th>ARTIST</th><th>COUNTRY</th><th>COMPANY</th><th>PRICE</th><th>YEAR</th></tr>';
        for (let i = 0; i < rowData.length; i++) {
            content += '<tr><td>' + rowData[i].getElementsByTagName('TITLE')[0].childNodes[0].nodeValue + '</td>';
            content += '<td>' + rowData[i].getElementsByTagName('ARTIST')[0].childNodes[0].nodeValue + '</td>';
            content += '<td>' + rowData[i].getElementsByTagName('COUNTRY')[0].childNodes[0].nodeValue + '</td>';
            content += '<td>' + rowData[i].getElementsByTagName('COMPANY')[0].childNodes[0].nodeValue + '</td>';
            content += '<td>' + rowData[i].getElementsByTagName('PRICE')[0].childNodes[0].nodeValue + '</td>';
            content += '<td>' + rowData[i].getElementsByTagName('YEAR')[0].childNodes[0].nodeValue + '</td></tr>';
        }
        content += '</table>'
        ajaxTableSection.innerHTML = content;
    }
    xhttp.open("GET", "catalog.xml");
    xhttp.send();
}

function showSlides(n) {
    if (n > slides.length) { slideIndex = 1 }
    if (n < 1) { slideIndex = slides.length }
    for (let i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    for (let i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
    }
    slides[slideIndex - 1].style.display = "block";
    dots[slideIndex - 1].className += " active";
}
function autoShowSlides() {
    for (let i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    for (let i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
    }
    index++;
    if (index > slides.length) { index = 1 }
    slides[index - 1].style.display = "block";
    if (dots.length > 0) {
        dots[index - 1].className += " active";
    }
    setTimeout(autoShowSlides, 3000); // Change image every 3 seconds
}

function renderMiddleware(ctx, next) {
    ctx.render = (content) => render(content, document.getElementById('site-content'));
    ctx.setUserNav = setUserNav;
    next();
}

function setUserNav() {
    const username = sessionStorage.getItem('username');
    const profile = Array.from(document.getElementsByClassName('profile'));
    const guest = Array.from(document.getElementsByClassName('guest'));
    if (username) {
        document.getElementById('welcome').textContent = `Добре дошъл, ${username}`;
        profile.forEach(element => {
            element.style.display = 'block';
        });
        guest.forEach(element => {
            element.style.display = 'none';
        });
    } else {
        profile.forEach(element => {
            element.style.display = 'none';
        });
        guest.forEach(element => {
            element.style.display = 'block';
        });
    }
}