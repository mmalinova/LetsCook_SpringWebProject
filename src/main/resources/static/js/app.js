let index = 0, slideIndex = 1;
const slides = document.getElementsByClassName("mySlides");
const dots = document.getElementsByClassName("dot");
const cat = Array.from(document.getElementsByClassName('cat'));

window.addEventListener('load', (event) => {
    let i;
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
        }
    }
});

function loadRecipes() {
    let ul = document.getElementById('last-added-recipes');
    fetch("http://localhost/programming/Let'sCook/api/recipe/read.php", {
        method: 'GET',
        headers: {
            "Content-Type": "application/json; charset=UTF-8",
        }
    }).then(function (response) {
        if (response.status !== 200) {
            alert('Проблем с кода от заявката');
            return;
        }
        // Examine the text in the response
        return response.text();
    }).then(function (text) {
        let content = '';
        const jsonData = JSON.parse(text);
        for (let i = 0; i < 6; i++) {
            const recipe = jsonData[i];
            let name = recipe.name;
            let recipe_id = recipe.id;
            fetch("http://localhost/programming/Let'sCook/api/photo/read.php?recipe_id=" + recipe_id, {
                method: 'GET',
                headers: {
                    "Content-Type": "application/json; charset=UTF-8",
                }
            }).then(function (response) {
                if (response.status !== 200) {
                    alert('Проблем с кода от заявката');
                    return;
                }
                // Examine the text in the response
                return response.text();
            }).then(function (text) {
                const jsonData = JSON.parse(text);
                const photo = jsonData[0];
                let img = photo.image_url;
                content += '<a href="/recipes/recipes_dashboard/' + recipe_id + '" class="cat" name="catt"> <img src="' + img + '" alt="recipeImg" /> <span class="catHover"></span><span class="iconHold">' + name + '</span> </a>';
                if (ul) {
                    ul.innerHTML = content;
                }
            }).then(function (text) {
                const catt = Array.from(document.getElementsByName('catt'));
            }).catch(function (error) {
                alert(error);
            });
        }
    }).catch(function (error) {
        alert(error);
    });
}

function showSlides(n) {
    if (n > slides.length) {
        slideIndex = 1
    }
    if (n < 1) {
        slideIndex = slides.length
    }
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
    if (index > slides.length) {
        index = 1
    }
    slides[index - 1].style.display = "block";
    if (dots.length > 0) {
        dots[index - 1].className += " active";
    }
    setTimeout(autoShowSlides, 3000); // Change image every 3 seconds
}