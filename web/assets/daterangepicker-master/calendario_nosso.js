$(document).ready(function () {
  $(function () {
      var hoje = new Date();
      var dataAtual = hoje.getDate() + '/' + (hoje.getMonth() + 1) + '/' + hoje.getFullYear();
      var dataFinal = (hoje.getDate() + 1) + '/' + (hoje.getMonth() + 1) + '/' + hoje.getFullYear();
      $(".dt_entrada").val(dataAtual);
      $(".dt_saida").val(dataFinal);

      $('#daterange').daterangepicker({
          "startDate": dataAtual,
          "endDate": dataFinal,
          "minDate": dataAtual,
          opens: 'center',
          locale: {
              format: 'DD/MM/YYYY'
          }
      }, function (start, end, label) {
          $(".dt_entrada").val(start.format('DD/MM/YYYY'));
          $(".dt_saida").val(end.format('DD/MM/YYYY'));
      });
  });
});