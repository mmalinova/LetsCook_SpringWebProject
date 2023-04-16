const recipeId = document.getElementById('recipeId').value
const commentForm = document.getElementById('commentForm')
commentForm.addEventListener("submit", handleFormSubmission)

const csrfHeaderName = document.head.querySelector('[name=_csrf_header]').content
const csrfHeaderValue = document.head.querySelector('[name=_csrf]').content

const commentContaier = document.getElementById('commentCnr')

async function handleFormSubmission(event) {
    event.preventDefault()

    const messageVal = document.getElementById('message').value

    fetch(`http://localhost:8080/api/${recipeId}/comments`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            [csrfHeaderName]: csrfHeaderValue
        },
        body: JSON.stringify({
            message: messageVal
        })
    }).then(res => res.json())
        .then(data => {
            document.getElementById('message').value = ""
            commentContaier.innerHTML += commentAsHtml(data)
        })
}

function commentAsHtml(comment) {
    let commentHtml = ""
    if (comment.authorName) {
        commentHtml = '<div>\n'
        commentHtml += `<h4>${comment.authorName}</h4>\n`
        commentHtml += `<p>${comment.message}</p>\n`
        commentHtml += '</div>\n'
    } else {
        commentHtml = '<div>\n'
        commentHtml += `<p style="color: red">Съобщението трябва да съдържа поне 1 символ и да си влезнал в профила си потребител!</p>\n`
        commentHtml += '</div>\n'
    }
    return commentHtml
}

fetch(`http://localhost:8080/api/${recipeId}/comments`, {
    headers: {
        "Accept": "application/json"
    }
}).then(res => res.json())
    .then(data => {
        for (let comment of data) {
            commentContaier.innerHTML += commentAsHtml(comment)
        }
    })