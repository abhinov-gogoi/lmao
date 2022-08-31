package com.netcat.meow.Email;

public class EmailTemplate {

    public static String VERIFICATION(String name, String link) {
        return "<!DOCTYPE HTML\n" +
                "  PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\"\n" +
                "  xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
                "\n" +
                "<head>\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "  <meta name=\"x-apple-disable-message-reformatting\">\n" +
                "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "  <title></title>\n" +
                "  <style type=\"text/css\">\n" +
                "    @media only screen and (min-width: 620px) {\n" +
                "      .u-row {\n" +
                "        width: 600px !important;\n" +
                "      }\n" +
                "\n" +
                "      .u-row .u-col {\n" +
                "        vertical-align: top;\n" +
                "      }\n" +
                "\n" +
                "      .u-row .u-col-33p33 {\n" +
                "        width: 199.98px !important;\n" +
                "      }\n" +
                "\n" +
                "      .u-row .u-col-50 {\n" +
                "        width: 300px !important;\n" +
                "      }\n" +
                "\n" +
                "      .u-row .u-col-100 {\n" +
                "        width: 600px !important;\n" +
                "      }\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    @media (max-width: 620px) {\n" +
                "      .u-row-container {\n" +
                "        max-width: 100% !important;\n" +
                "        padding-left: 0px !important;\n" +
                "        padding-right: 0px !important;\n" +
                "      }\n" +
                "\n" +
                "      .u-row .u-col {\n" +
                "        min-width: 320px !important;\n" +
                "        max-width: 100% !important;\n" +
                "        display: block !important;\n" +
                "      }\n" +
                "\n" +
                "      .u-row {\n" +
                "        width: calc(100% - 40px) !important;\n" +
                "      }\n" +
                "\n" +
                "      .u-col {\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "\n" +
                "      .u-col>div {\n" +
                "        margin: 0 auto;\n" +
                "      }\n" +
                "    }\n" +
                "\n" +
                "    body {\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "    }\n" +
                "\n" +
                "    table,\n" +
                "    tr,\n" +
                "    td {\n" +
                "      vertical-align: top;\n" +
                "      border-collapse: collapse;\n" +
                "    }\n" +
                "\n" +
                "    p {\n" +
                "      margin: 0;\n" +
                "    }\n" +
                "\n" +
                "    .ie-container table,\n" +
                "    .mso-container table {\n" +
                "      table-layout: fixed;\n" +
                "    }\n" +
                "\n" +
                "    * {\n" +
                "      line-height: inherit;\n" +
                "    }\n" +
                "\n" +
                "    a[x-apple-data-detectors='true'] {\n" +
                "      color: inherit !important;\n" +
                "      text-decoration: none !important;\n" +
                "    }\n" +
                "\n" +
                "    table,\n" +
                "    td {\n" +
                "      color: #000000;\n" +
                "    }\n" +
                "\n" +
                "    a {\n" +
                "      color: #0000ee;\n" +
                "      text-decoration: underline;\n" +
                "    }\n" +
                "\n" +
                "    @media (max-width: 480px) {\n" +
                "      #u_content_menu_5 .v-padding {\n" +
                "        padding: 16px 18px 10px !important;\n" +
                "      }\n" +
                "\n" +
                "      #u_content_text_4 .v-text-align {\n" +
                "        text-align: center !important;\n" +
                "      }\n" +
                "\n" +
                "      #u_content_text_12 .v-text-align {\n" +
                "        text-align: left !important;\n" +
                "      }\n" +
                "\n" +
                "      #u_content_text_19 .v-text-align {\n" +
                "        text-align: left !important;\n" +
                "      }\n" +
                "\n" +
                "      #u_content_image_10 .v-src-width {\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "\n" +
                "      #u_content_image_10 .v-src-max-width {\n" +
                "        max-width: 100% !important;\n" +
                "      }\n" +
                "\n" +
                "      #u_content_text_30 .v-text-align {\n" +
                "        text-align: center !important;\n" +
                "      }\n" +
                "\n" +
                "      #u_content_text_33 .v-text-align {\n" +
                "        text-align: center !important;\n" +
                "      }\n" +
                "\n" +
                "      #u_content_text_31 .v-text-align {\n" +
                "        text-align: center !important;\n" +
                "      }\n" +
                "\n" +
                "      #u_content_text_34 .v-text-align {\n" +
                "        text-align: center !important;\n" +
                "      }\n" +
                "\n" +
                "      #u_content_text_29 .v-text-align {\n" +
                "        text-align: center !important;\n" +
                "      }\n" +
                "\n" +
                "      #u_content_text_32 .v-text-align {\n" +
                "        text-align: center !important;\n" +
                "      }\n" +
                "    }\n" +
                "  </style>\n" +
                "\n" +
                "  <link href=\"https://fonts.googleapis.com/css?family=Cabin:400,700&display=swap\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body class=\"clean-body u_body\"\n" +
                "  style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #ffffff;color: #000000\">\n" +
                "  <table\n" +
                "    style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #ffffff;width:100%\"\n" +
                "    cellpadding=\"0\" cellspacing=\"0\">\n" +
                "    <tbody>\n" +
                "      <tr style=\"vertical-align: top\">\n" +
                "        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n" +
                "          <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
                "            <div class=\"u-row\"\n" +
                "              style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">\n" +
                "              <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n" +
                "                <div class=\"u-col u-col-100\"\n" +
                "                  style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
                "                  <div style=\"width: 100% !important;\">\n" +
                "                    <div\n" +
                "                      style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                "                      <table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              style=\"overflow-wrap:break-word;word-break:break-word;padding:21px 10px 20px;font-family:arial,helvetica,sans-serif;\"\n" +
                "                              align=\"left\">\n" +
                "                              <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "                                <tr>\n" +
                "                                  <td class=\"v-text-align\" style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\">\n" +
                "\n" +
                "                                    <img align=\"center\" border=\"0\"\n" +
                "                                      src=\"https://logodix.com/logo/1776730.png\"\n" +
                "                                      alt=\"Image\" title=\"Image\"\n" +
                "                                      style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 29%;max-width: 168.2px;\"\n" +
                "                                      width=\"168.2\" class=\"v-src-width v-src-max-width\" />\n" +
                "\n" +
                "                                  </td>\n" +
                "                                </tr>\n" +
                "                              </table>\n" +
                "\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "          </div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "          <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
                "            <div class=\"u-row\"\n" +
                "              style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #e4faf4;\">\n" +
                "              <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n" +
                "                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #e4faf4;\"><![endif]-->\n" +
                "\n" +
                "                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "                <div class=\"u-col u-col-100\"\n" +
                "                  style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
                "                  <div style=\"width: 100% !important;\">\n" +
                "                    <!--[if (!mso)&(!IE)]><!-->\n" +
                "                    <div\n" +
                "                      style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                "                      <!--<![endif]-->\n" +
                "\n" +
                "                      <table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              style=\"overflow-wrap:break-word;word-break:break-word;padding:45px 10px 10px;font-family:arial,helvetica,sans-serif;\"\n" +
                "                              align=\"left\">\n" +
                "\n" +
                "                              <div class=\"v-text-align\"\n" +
                "                                style=\"line-height: 140%; text-align: center; word-wrap: break-word;\">\n" +
                "                                <p style=\"font-size: 14px; line-height: 140%;\"><span\n" +
                "                                    style=\"font-family: Cabin, sans-serif; font-size: 20px; line-height: 28px;\">Hi\n" +
                "                                    " + name + " !! <br>Welcome\n" +
                "                                    to LMAO</span></p>\n" +
                "                              </div>\n" +
                "\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "\n" +
                "                      <table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              style=\"overflow-wrap:break-word;word-break:break-word;padding:0px 10px 10px;font-family:arial,helvetica,sans-serif;\"\n" +
                "                              align=\"left\">\n" +
                "\n" +
                "                              <div class=\"v-text-align\"\n" +
                "                                style=\"color: #4c5763; line-height: 180%; text-align: center; word-wrap: break-word;\">\n" +
                "                                <p style=\"font-size: 14px; line-height: 180%;\">Please verify your email by clicking the\n" +
                "                                  button</p>\n" +
                "                                <p style=\"font-size: 14px; line-height: 180%;\">We won't spam you !! Hehehe</p>\n" +
                "                              </div>\n" +
                "\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "\n" +
                "                      <table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:arial,helvetica,sans-serif;\"\n" +
                "                              align=\"left\">\n" +
                "\n" +
                "                              <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "                                <tr>\n" +
                "                                  <td class=\"v-text-align\" style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\">\n" +
                "\n" +
                "                                    <img align=\"center\" border=\"0\"\n" +
                "                                      src=\"https://logodix.com/logo/1776730.png\"\n" +
                "                                      alt=\"Image\" title=\"Image\"\n" +
                "                                      style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 60%;max-width: 348px;\"\n" +
                "                                      width=\"348\" class=\"v-src-width v-src-max-width\" />\n" +
                "\n" +
                "                                  </td>\n" +
                "                                </tr>\n" +
                "                              </table>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "\n" +
                "                      <table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              style=\"overflow-wrap:break-word;word-break:break-word;padding:15px 10px 40px;font-family:arial,helvetica,sans-serif;\"\n" +
                "                              align=\"left\">\n" +
                "\n" +
                "                              <div class=\"v-text-align\" align=\"center\">\n" +
                "                                <a href=\"" + link + "\" target=\"_blank\"\n" +
                "                                  style=\"box-sizing: border-box;display: inline-block;font-family:arial,helvetica,sans-serif;text-decoration: none;-webkit-text-size-adjust: none;text-align: center;color: #FFFFFF; background-color: #00d09c; border-radius: 1px;-webkit-border-radius: 1px; -moz-border-radius: 1px; width:auto; max-width:100%; overflow-wrap: break-word; word-break: break-word; word-wrap:break-word; mso-border-alt: none;\">\n" +
                "                                  <span class=\"v-padding\"\n" +
                "                                    style=\"display:block;padding:11px 25px;line-height:120%;\"><span\n" +
                "                                      style=\"font-size: 14px; line-height: 16.8px;\">Verify Email</span></span>\n" +
                "                                </a>\n" +
                "                              </div>\n" +
                "\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "          </div>\n" +
                "\n" +
                "          <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
                "            <div class=\"u-row\"\n" +
                "              style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">\n" +
                "              <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n" +
                "                <div class=\"u-col u-col-100\"\n" +
                "                  style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
                "                  <div style=\"width: 100% !important;\">\n" +
                "                    <div\n" +
                "                      style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                "                      <table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              style=\"overflow-wrap:break-word;word-break:break-word;padding:21px;font-family:arial,helvetica,sans-serif;\"\n" +
                "                              align=\"left\">\n" +
                "\n" +
                "                              <table height=\"0px\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
                "                                style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 0px solid #BBBBBB;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n" +
                "                                <tbody>\n" +
                "                                  <tr style=\"vertical-align: top\">\n" +
                "                                    <td\n" +
                "                                      style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n" +
                "                                      <span>&#160;</span>\n" +
                "                                    </td>\n" +
                "                                  </tr>\n" +
                "                                </tbody>\n" +
                "                              </table>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "          </div>\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </tbody>\n" +
                "  </table>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }

    public static String PASSWORD_RESET(String name, String reset_link) {
        return "Hello " + name + ". Here's the link to reset your password " + reset_link;
    }
}
