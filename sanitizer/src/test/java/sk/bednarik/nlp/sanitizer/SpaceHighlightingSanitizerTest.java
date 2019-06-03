package sk.bednarik.nlp.sanitizer;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

public class SpaceHighlightingSanitizerTest {

  @Test
  public void testSanitization() {
    assertThat(SpaceHighlightingSanitizer.sanitize("P o u č e n i e")).isEqualTo("Poučenie");
    assertThat(SpaceHighlightingSanitizer.sanitize("3 a 4")).isEqualTo("3 a 4");
    assertThat(SpaceHighlightingSanitizer.sanitize("R O Z H O D N U T I E")).isEqualTo("ROZHODNUTIE");
    assertThat(SpaceHighlightingSanitizer.sanitize("O d ô v o d n e n i e")).isEqualTo("Odôvodnenie");
    assertThat(SpaceHighlightingSanitizer.sanitize("Išiel domov a v tom sa pokazila električka."))
        .isEqualTo("Išiel domov a v tom sa pokazila električka.");
    assertThat(SpaceHighlightingSanitizer.sanitize("s a     n e b u d e    p o s u d z o v a ť"))
        .isEqualTo("sa nebude posudzovať");
    System.out.println(SpaceHighlightingSanitizer.sanitize("\n" +
        "                                            R O Z H O D N U T I E\n" +
        "\n" +
        "\n" +
        "Okresný  úrad Žiar nad Hronom, odbor starostlivosti o životné prostredie, ako príslušný orgán  štátnej správy na úseku posudzovania vplyvov na životné prostredie podľa § 56 písm. b) zákona č. 24/2006 Z. z.  o posudzovaní vplyvov na životné prostredie a o zmene a doplnení niektorých zákonov v znení neskorších predpisov,  v súlade s   § 2 ods. 3, ods. 6 a § 3 ods. 1 písm. e) zákona č. 180/2013 o organizovaní miestnej štátnej správy a o zmene a doplnení  niektorých zákonov a  podľa  § 5 ods. 1 zákona NR SR č. 525/2003 Z. z. o štátnej správe starostlivosti o životné prostredie v znení neskorších predpisov, ako príslušný orgán štátnej správy  na základe predloženého zámeru navrhovanej činnosti „ Výkup železných a neželezných kovov, papiera “,  ktorý  predložila navrhovateľka  Anita Hrmová – AEM zberné suroviny, Veternícka 169/66, 967 01 Kremnica,  IČO 00321125,   vykonal  zisťovacie konanie podľa § 29 ods. 1 zákona NR SR č. 24/2006 Z. z. o posudzovaní vplyvov na životné prostredie v znení neskorších predpisov  ( ďalej len „ zákon“ ) vydáva podľa § 29 zákona  toto rozhodnutie:\n"
        +
        "\n" +
        "             Navrhovaná činnosť „Výkup železných a neželezných kovov, papiera “, ktorej účelom je výkup železných a neželezných kovov, papiera, ich dočasné zhromaždenie a uskladnenie, navrhovaná  v zastavanom území Mesta Kremnica,   na pozemkoch C KN par. č. 1878/1, 1878/2 a 1878/4\n"
        +
        "                                       s a     n e b u d e    p o s u d z o v a ť  \n" +
        "\n" +
        "podľa  zákona.  Pre uvedenú činnosť  je možné požiadať o povolenie podľa osobitných predpisov. Pre realizáciu sa navrhuje variant č. A pre ktorý sa stanovujú tieto  podmienky.  \n"
        +
        "\n" +
        "        Pri príprave dokumentácie stavby k stavebnému konaniu a v procese konania o povolení  činnosti podľa osobitných predpisov je potrebné zohľadniť v plnej miere  rešpektovať pripomienky, ktoré vyplynuli zo stanovísk doručených k zámeru a z opatrení navrhnutých v zámere: \n"
        +
        "\n" +
        "1. Dodržať a realizovať  opatrenia na zmiernenie nepriaznivých vplyvov navrhovanej činnosti na životné prostredie   počas prevádzky  navrhnuté v kapitole   10. Zámeru.\n"
        +
        "\n" +
        "2. V žiadosti o vydanie súhlasu povoľujúcim orgánom stanoviť kapacitu zariadenia a     vymedziť  jednotlivé druhy odpadov podľa katalógových čísiel s ktorými bude nakladané  v navrhovanom zariadení v zmysle platných právnych predpisov.  \n"
        +
        "3. Prevádzkou činnosti  nesmú byť prekročené prípustné hodnoty určujúcich veličín hluku vo vonkajšom prostredí  uvedené vo Vyhláške  MZ SR č.549/2007 Z. z., \n"
        +
        "\n" +
        "4. Pri realizácií zámeru a prevádzkovaní navrhovanej činnosti vykonať opatrenia na ochranu existujúcich drevín na pozemku par. č. 1878//4 v zmysle § 47 zákona č. 543/2002 Z. z. o ochrane prírody a krajiny. \n"
        +
        "                                           O d ô v o d n e n i e\n" +
        "             Navrhovateľ, Anita Hrmová – AEM zberné suroviny, Veternícka 169/66, 967 01 Kremnica,  IČO 00321125 požiadala   dňa 17.04.2015 Okresný úrad Žiar nad Hronom, odbor starostlivosti o životné prostredie, o  prerokovania zámeru „ Výkup a skladovanie železných  a neželezných  kovov, papiera “   v k. ú. Kremnica  na pozemkoch parcelné č. 1878/1, 1878/2\n"
        +
        "a 1878/4.  Nakoľko predmetné podanie nespĺňalo náležitosti  podľa ustanovení § 22 ods. 3 a 4 zákona, príslušný orgán vyzval  navrhovateľa o doplnenie zámeru činnosti v súlade s ustanoveniam § 22 ods. 3 a 4 zákona a nakoľko zámer činnosti nebol doplnený v stanovenom termíne, príslušný orgán rozhodnutím pod č. s. : OU-ZH-OSZP- 2015/004740  ( č. záznamu: 2015/0018401 )  dňa 02.06.2015 v danej veci prerušil správne konanie.  Dňa 29.06.2015 navrhovateľ doplnil zámer činnosti v súlade s ustanoveniami § 22 zákona a požiadal o vykonanie zisťovacieho konania podľa zákona. \n"
        +
        "\n" +
        "          Navrhovaná činnosť, ktorú  vypracoval  Ing. Vladimír Slančík, Jabloňová 1355/16, 960 01 Zvolen  v  06/2015  je  podľa  prílohy   č. 8  k zákona je zaradená  do kapitoly 9. Infraštruktúra, pol. č. 10 Zhromažďovanie odpadov zo železných kovov, z neželezných kovov alebo starých vozidiel  v časti B zisťovacie konanie  je bez limitu  v zmysle § 18 ods. 2 písm. b) zákona o posudzovaní podlieha  zisťovaciemu konaniu podľa § 29 zákona.     \n"
        +
        "\n" +
        "               \n" +
        "P o u č e n i e\n" +
        "\n" +
        "Proti tomuto rozhodnutiu možno podať odvolanie podľa § 53 a § 54 zákona č. 71/1967 Zb. o správnom konaní (správny poriadok) v znení neskorších predpisov na Okresnom úrade Žiar \n"
        +
        "\n" +
        "nad Hronom, odbor starostlivosti o životné prostredie v lehote do 15 dní odo dňa doručenia \n" +
        "rozhodnutia účastníkovi konania. V prípade verejnosti podľa § 24 ods. 4 zákona sa za deň doručenia rozhodnutia považuje 15. deň  zverejnenia rozhodnutia vydaného v zisťovacom konaní podľa § 29 ods. 15 zákona.  Toto rozhodnutie je preskúmateľné súdom podľa zákona č. 99/1963 Zb.  Občiansky súdny poriadok v znení neskorších predpisov až po vyčerpaní riadnych opravných prostriedkov. \n"
        +
        "                                                                                                             Ing. Dušan Berkeš  \n"
        +
        "\n" +
        "\n" +
        "\n" +
        "                        \n" +
        "\n" +
        "                              vedúci odboru\n" +
        "Doručuje sa:\n" +
        "\n" +
        "1.  Anita Hrmová – AEM zberné suroviny, Veternícka 169/66,  967 01 Kremnica \n" +
        "2.  Ministerstvo životného prostredia SR, Nám. Ľ. Štúra 1, 827 15 Bratislava \n" +
        "3.  Mesto Kremnica, Štefánikovo námestie 1/1, 967 01 Kremnica   \n" +
        "4.  Krajský pamiatkový úrad Banská Bystrica, Lazovná ulica č. 8, 975 65 Banská Bystrica  \n" +
        "\n" +
        "5.  Obvodný banský úrad  Banská Bystrica, ul. 9. Mája 2, 975 65 Banská Bystrica\n" +
        "6.  Regionálny úrad verejného zdravotníctva, Sládkovičova 484/9,965 24 Žiar nad Hronom  \n" +
        "\n" +
        "7.  Okresné riaditeľstvo hasičského  a záchranného  zboru v Žiari nad Hronom, Ul.  \n" +
        "\n" +
        "     Priemyselná 12, 965 01 Žiar nad Hronom                                                                                                       \n"
        +
        "\n" +
        "8.  Okresný úrad Žiar nad Hronom, odbor krízového riadenia, Nám. M. Slovenskej č.8, 965 01\n" +
        "\n" +
        "      Žiar nad Hronom \n" +
        "9.  Okresný úrad Žiar nad Hronom, pozemkový a lesný odbor, Nám. M. Slovenskej č.8,\n" +
        "\n" +
        "       965 01 Žiar nad Hronom\n" +
        "\n" +
        "10. Okresný  úrad Žiar nad Hronom, odbor starostlivosti o životné prostredie - štátna vodná \n" +
        "       správa, štátna správa ochrany prírody a krajiny, štátna správa odpadového hospodárstva, \n" +
        "       štátna správa ochrany ovzdušia \n" +
        "11.  Združenie domových samospráv, P.O.BOX 218, 850 00 Bratislava - Petržalka\n" +
        "\n"));
  }
}
