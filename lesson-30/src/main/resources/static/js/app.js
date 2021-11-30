$(document).ready(function () {
  $("#btnOpenList").click(function () {
    clearListBookArea()
    clearBookCreateArea()
    hideBookCreateArea()
    btnOpenListOnClick();
    showListBookArea()
  });

  $("#btnCreateBook").click(function () {
    clearListBookArea()
    clearBookCreateArea()
    hideListBookArea()
    btnCreateBookOnClick()
    showBookCreateArea()
  });

  $("#btnSaveBook").click(function () {
    btnSaveBookOnClick()
    hideBookCreateArea()
    hideListBookArea()
  });
});

function clearListBookArea() {
  $('#bookListArea').empty()
}

function showListBookArea() {
  $('#bookListForm').attr('hidden', false);
}

function hideListBookArea() {
  $('#bookListForm').attr('hidden', true);
}

function clearBookCreateArea() {
  $('#author-input').find('option').remove().end()
  $('#genre-input').find('option').remove().end()
  $('#title-input').val("");
}

function hideBookCreateArea() {
  $('#bookCreateArea').attr('hidden', true)
}

function showBookCreateArea() {
  $('#bookCreateArea').attr('hidden', false)
}

function btnOpenListOnClick() {
  $.getJSON('/books').done(function (books) {
    books.forEach(function (book) {
      let genreElement = book.genres.map((genre) => {
        return '<li class="list-group">' + genre.name + '</li>'
      }).join('')

      let authorElement = book.authors.map((author) => {
        return '<li class="list-group">' + author.fullName + '</li>'
      }).join('')

      $('#bookListArea').append(`
               <tr>
               <td>${book.id}</td>
               <td>${book.title}</td>
               <td>${genreElement}</td>
               <td>${authorElement}</td>
               </tr>
        `)
    });
  });
}

function btnCreateBookOnClick() {
  $.getJSON('/authors').done(function (authors) {
    authors.forEach(function (author) {
      let element = '<option value=' + author.id + '>' + author.id + '</option>'
      $('#author-input').prepend(element);
    })
  })

  $.getJSON('/genres').done(function (genres) {
    genres.forEach(function (genre) {
      let element = '<option value=' + genre.id + '>' + genre.id + '</option>'
      $('#genre-input').prepend(element);
    })
  })
}

function btnSaveBookOnClick() {
  let newBook = Object();
  newBook.title = $('#title-input').val();
  newBook.genreId = $('#genre-input').val();
  newBook.authorId = $('#author-input').val();

  $.post({
    url: '/book',
    type: 'post',
    dataType: 'json',
    contentType: 'application/json; charset=utf-8',
    data: JSON.stringify(newBook),
  });
}
