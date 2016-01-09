window.onload = function () {
    var isbn = getQueryParam('isbn');
    if (isbn) {
        /*用jquery 实现GET方式调用api请求




         */
         $.ajax({
                     type: "GET",
                     url:baseUrl+"/"+isbn,
                     success:function(book){
                       console.log(book);
                       var title = document.getElementsByName("Title")[0]
                       title.value = book.title
                       var author = document.getElementsByName("Author")[0]
                       author.value = book.author
                       var isbn = document.getElementsByName("ISBN")[0]
                       isbn.value = book.isbn
                       var price = document.getElementsByName("Price($)")[0]
                       price.value = book.price
                     },
                     dataType:"json"
                  });
    }

    var form = document.querySelector('.form');
    form.addEventListener('submit', function (e) {
        e.preventDefault();
        var formElements = e.target.elements;
        var book = {};
        for (var i = 0; i < formElements.length - 1; ++i) {
            book[tableHeaderMapper[formElements[i].name]] = formElements[i].value;
        }
        /*if (isbn) {
            *//*用jquery 实现PUT方式调用api请求




            *//*
        } else {
            $.ajax({
                type: "POST",
                url: baseUrl,
                data: JSON.stringify(book),
                contentType: "application/json; charset=utf-8",
                success: function () {
                    location.href = '/index.html';
                }
            });
        }*/
        var title = document.getElementsByName("Title")[0]
                    book.title = title.value
                    var author = document.getElementsByName("Author")[0]
                    book.author = author.value
                    var isbn = document.getElementsByName("ISBN")[0]
                    book.isbn = isbn.value
                    var price = document.getElementsByName("Price($)")[0]
                    book.price = price.value
                    $.ajax({
                                type: "PUT",
                                url:baseUrl+"/"+isbn.value,
                                data:JSON.stringify(book),
                                contentType: "application/json; charset=utf-8",
                                success:function(){
                                  location.href = '/index.html'
                                }
                             });
    });
};
