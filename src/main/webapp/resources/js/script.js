var BOOKS; //in displayBookTable() to this var was written a list of all books


$(document).ready(function () {

    displayBookTable();
    displayUserTable();
    $("#BookHistoryButton").click(function () {
        // var data = {};
        // data = {"userId": $("#userId").val() /*"password":$("#unreturned").val()*/};
        //
        $.ajax
        ({
            type: "POST",//Метод передачи
            //   data: data,//Передаваемые данные в JSON - формате
            url: 'BookHistory/' + $("#userId").val(),//Название сервлета
            success: function (serverData)//Если запрос удачен
            {
                function displayBookName(bookHistory) {
                    let name;
                    BOOKS.forEach(function (book) {

                        if (book.id === bookHistory.bookId) {
                            name = book.bookName;
                        }
                    });
                    return name;
                };

                function displayReturnDate(bookHistory) {
                    if (bookHistory.returnDate === null) {
                        return "Book wasn`t returned";
                    } else {
                        return bookHistory.returnDate.year+ " " + bookHistory.returnDate.month+ " " + bookHistory.returnDate.dayOfMonth;
                    }
                }

                function serverMap() {
                    return serverData.map(bookHistory =>
                        `<tr>
      <td>${bookHistory.bookId}</td> 
      <td>
      ${displayBookName(bookHistory)}
          </td>
      <td>${bookHistory.takeDate.year+ " " + bookHistory.takeDate.month+ " " + bookHistory.takeDate.dayOfMonth}  </td> 
       <td>${displayReturnDate(bookHistory)}  </td> 
    </tr>`);
                }

                $('#bookStoryTable').append(
                    `<tbody>${serverMap().join('')} </tbody>`);
            }
        });
    })
});

//               $('#bookStoryTable').append(
//                   `<tbody>${serverData.map(n =>
//
//                       `<tr>
//     <td>${n.bookId}</td>
//     <td>
//     ${q()}
//         </td>
//     <td>${getTakeData(n)}  </td>
//   </tr>`).join('')}
// </tbody>`
//               );
//           },
//       });

//   });
//);

function displayBookTable() {
    $.ajax
    ({
        type: "POST",//Метод передачи
        contentType: "application/json;charset=utf-8",
        data: {}, //Передаваемые данные в JSON - формате
        url: 'Book/display',//Название сервлета
        dataType: "json",
        error: function (a, b, c) {
            console.log("error start");
            console.log(a);
            console.log(b);
            console.log(c);
            console.log("error finish")
        },
        success: function (BookData)//Если запрос удачен
        {
            BOOKS = BookData;
            $('#bookTable').append(
                `<tbody> ${BookData.map(n =>
                    `<tr>
      <td>${n.id}</td>
      <td>${n.bookName}</td>
      <td>${n.author}</td>
      <td>${n.lost}</td>
      <td>${n.del}</td>
    </tr>`).join('')}
  </tbody>`)
        }
    })
}

function displayUserTable() {
    $.ajax
    ({
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        type: "POST",//Метод передачи
        data: {},//Передаваемые данные в JSON - формате
        url: 'Customer/display',//Название сервлета
        error: function (a, b, c) {
            console.log("error start");
            console.log(a);
            console.log(b);
            console.log(c);
            console.log("error finish")
        },
        success: function (userData)//Если запрос удачен
        {
            $('#userTable').append(
                `<tbody>${userData.map(n =>
                    `<tr>
      <td>${n.id}</td>
      <td>${n.customerName}</td>
      <td>${n.customerAddress}</td>
    </tr>`).join('')}
  </tbody>`)
        }
    })
}

//function slowScroll(id) {
//    console.log("33333");
//    $('html, body').animate({
//
//        scrollTop: $(id).offset().top
//    }, 500);
//}

$(document).on("scroll", function () {

    if ($(window).scrollTop() === 0)
        $("header").removeClass("fixed");
    else
        $("header").attr("class", "fixed");
});

function addBook() {
    $('#bookOptions').append(
        " <form id='bookAddForm' >\n" +
        "    <br>    <label for=\"bookName\">Input book name</label>\n<br>" +
        "        <br> <input type=\"text\" name=\"bookName\" id=\"bookName\" <br>" +
        "      <br>  <label for=\"bookAuthor\">Input book author</label>\n<br>" +
        "        <br> <input type=\"text\" name=\"bookAuthor\" id=\"bookAuthor\"<br> " +
        "        <br> <input type=\"button\" onclick=\"sendNewBook()\" id =\"bookAddButton \" value=\"send\">\n<br>" +
        "    </form>"
    )
}

function sendNewBook() {

    let data = {"bookName": $("#bookName").val(), "bookAuthor": $("#bookAuthor").val(), "data": "addBook"};
    $.ajax
    ({
        type: "POST",//Метод передачи
        data: JSON.stringify(data),//Передаваемые данные в JSON - формате
        url: 'Book/add',//Название сервлета
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (BookData)//Если запрос удачен
        {
            $('#bookAddForm').remove();
            $('#bookOptions').append("<p> Book was added</p>");
        }
    });
}

function deleteBook() {
    $('#bookOptions').append(
        " <form id='bookDeleteForm' >\n" +
        "    <br>    <label for=\"bookName\">Input book id</label>\n<br>" +
        "        <br> <input type=\"number\" name=\"bookId\" id=\"bookId\" <br>" +
        "        <br> <input type=\"button\" onclick=\"sendDeleteBook()\" id =\"bookEditButton \" value=\"send\">\n<br>" +
        "    </form>")
}

function sendDeleteBook() {
    $.ajax
    ({
        type: "GET",//Метод передачи
        data: {},//Передаваемые данные в JSON - формате
        url: 'Book/delete/' + $("#bookId").val(),//Название сервлета
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (serverData)//Если запрос удачен
        {
            $('#bookDeleteForm').remove();
            $('#bookOptions').append("<p> Book was deleted</p>");
        }
    });
}

function editBook() {
    $('#bookOptions').append(
        " <form id='bookEditForm' >\n" +
        "    <br>  <label for=\"bookName\">Input book id</label>\n<br> " +
        "      <br> <input type=\"number\" name=\"bookId\" id=\"bookId\" <br>" +
        "    <br>  <label for=\"editBookName\">Input new book name</label>\n<br>  " +
        "      <br> <input type=\"text\" name=\"editBookName\" id=\"editBookName\" <br> " +
        "     <br> <label for=\"editBookAuthor\">Input new book author</label>\n <br>  " +
        "      <br> <input type=\"text\" name=\"editBookAuthor\" id=\"editBookAuthor\" <br>" +
        "      <br> <input type=\"button\" onclick=\"sendEditBook()\" id =\"bookEditButton \" value=\"send\">\n <br> " +
        "    </form>"
    )
}

function sendEditBook() {
    let data = {
        "bookName": $("#editBookName").val(),
        "bookAuthor": $("#editBookAuthor").val(),
        "bookId": $("#bookId").val()
        // "data": "editBook"
    };
    $.ajax
    ({
        type: "POST",//Метод передачи
        data: JSON.stringify(data),//Передаваемые данные в JSON - формате
        url: 'Book/edit',//Название сервлета
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (BookData)//Если запрос удачен
        {
            $('#bookEditForm').remove();
            $('#bookOptions').append("<p> Book was edited</p>");
        }
    });
}











