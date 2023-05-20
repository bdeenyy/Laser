// Найти форму на странице
const form = document.querySelector('form');

// Добавить обработчик события submit
form.addEventListener('submit', event => {
    // Отменить стандартное поведение формы
    event.preventDefault();

    // Создать объект FormData из формы
    const formData = new FormData(form);

    // Отправить данные формы на сервер с помощью AJAX
    fetch('/upload', {
        method: 'POST',
        body: formData
    })
        .then(response => response.json())
        .then(result => {
            // Найти элементы для отображения результата и изображения
            const lengthElement = document.querySelector('#length');
            const imageElement = document.querySelector('#uploadedImage');

            // Отобразить результат и изображение
            lengthElement.textContent = result.length;
            imageElement.src = 'data:image/svg+xml;base64,' + btoa(result.svg);
        });
});