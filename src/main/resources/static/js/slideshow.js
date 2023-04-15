let i;
let index = 0, slideIndex = 1;
const slides = document.getElementsByClassName("mySlides");
const dots = document.getElementsByClassName("dot");
window.addEventListener('load', (event) => {
    autoShowSlides();
});
// Next/previous controls
function plusSlides(n) {
    showSlides(slideIndex += n);
}
// Thumbnail image controls
function currentSlide(n) {
    showSlides(slideIndex = n);
}
function showSlides(n) {
    if (n > slides.length) { slideIndex = 1 }
    if (n < 1) { slideIndex = slides.length }
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
    }
    slides[slideIndex - 1].style.display = "block";
    dots[slideIndex - 1].className += " active";
}
function autoShowSlides() {
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
    }
    index++;
    if (index > slides.length) { index = 1 }
    slides[index - 1].style.display = "block";
    dots[index - 1].className += " active";
    setTimeout(autoShowSlides, 3000); // Change image every 3 seconds
}