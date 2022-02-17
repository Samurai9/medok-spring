$(document).ready(function () {
   $('#put_to_basket_form').submit(function (e) {
      var $form = $(this);
      $.ajax({
         type: $form.attr('method'),
         url: $form.attr('action'),
         data: $form.serialize()
      });
      e.preventDefault();
   });


   $('#delete_from_basket_form').submit(function (e) {
      var $form = $(this);
      $.ajax({
         type: $form.attr('method'),
         url: $form.attr('action'),
         data: $form.serialize()
      }).done(function() {
         document.location.reload();
      }).fail(function() {
         alert("Что-то пошло не так")
      });
      e.preventDefault();
   });
});
