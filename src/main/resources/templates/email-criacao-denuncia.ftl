<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <link href='https://fonts.googleapis.com/css?family=Koulen' rel='stylesheet'>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>NOTIFICA - Confirmação de Denúncia</title>
</head>

<body style="margin: 0; padding: 0; font-family: 'Arial', 'Helvetica', sans-serif; background-color: #ffffff;">

    <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#ffffff" align="center">
        <tr>
            <td align="center" valign="top" bgcolor="#2EA06A" style="padding: 20px 0 20px 0; color: #ffffff;">

                <table width="600" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td align="center" valign="top" bgcolor="#277340"
                            style="background-color: #2EA06A; padding: 20px; border-radius: 8px;">

                            <div style="font-size: 80px; color: #ffffff; font-weight: bold; font-family: 'KOULEN', sans-serif; letter-spacing: 2px;">
                                NOTIFICA
                            </div>

                            <div style="font-size: 20px; color: #ffffff; margin-top: 15px;">
                                <b>Confirmação de Denúncia <b><br>
                            </div>

                            <div style="font-size: 18px; color: #ffffff; margin-top: 20px; line-height: 1.6;">
                                <i>Olá ${nome}, obrigado por contribuir para a melhoria do abastecimento de água e saneamento básico em sua região usando o NOTIFICA. Sua denúncia foi postada e já está disponível para visualização!<i>
                            </div>

                            <div style="font-size: 18px; color: #ffffff; margin-top: 20px; line-height: 1.6;">
                                <b>Detalhes da Denúncia:</b>
                                <br>
                                <b>Status:</b> ${status}<br>
                                <b>Categoria:</b> ${categoria}<br>
                                <b>Tipo Denuncia:</b> ${tipo_denuncia}<br>
                                <b>Título:</b> ${titulo}<br>
                                <b>Descrição:</b> ${descricao}<br>
                            </div>

                        </td>
                    </tr>
                </table>

            </td>
        </tr>
    </table>

</body>

</html>


