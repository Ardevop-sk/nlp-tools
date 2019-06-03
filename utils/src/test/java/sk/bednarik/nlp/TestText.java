package sk.bednarik.nlp;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;

@Getter
public class TestText {

  private final String input;
  private final List<String> sentences;
  private final List<String> tokens;

  public TestText(String input, List<String> sentences, List<String> tokens) {
    this.input = input;
    this.sentences = sentences;
    this.tokens = tokens;
  }

  private static String in1 = "\n" +
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
      "\n";

  private static final List<String> sentences1 = Arrays.asList(
      "ROZHODNUTIE\n\n\n\nOkresný úrad Žiar nad Hronom, odbor starostlivosti o životné prostredie, ako príslušný orgán štátnej správy na úseku posudzovania vplyvov na životné prostredie podľa § 56 písm. b) zákona č. 24/2006 Z. z. o posudzovaní vplyvov na životné prostredie a o zmene a doplnení niektorých zákonov v znení neskorších predpisov, v súlade s § 2 ods. 3, ods. 6 a § 3 ods. 1 písm. e) zákona č. 180/2013 o organizovaní miestnej štátnej správy a o zmene a doplnení niektorých zákonov a podľa § 5 ods. 1 zákona NR SR č. 525/2003 Z. z. o štátnej správe starostlivosti o životné prostredie v znení neskorších predpisov, ako príslušný orgán štátnej správy na základe predloženého zámeru navrhovanej činnosti „ Výkup železných a neželezných kovov, papiera “, ktorý predložila navrhovateľka Anita Hrmová – AEM zberné suroviny, Veternícka 169/66, 967 01 Kremnica, IČO 00321125, vykonal zisťovacie konanie podľa § 29 ods. 1 zákona NR SR č. 24/2006 Z. z. o posudzovaní vplyvov na životné prostredie v znení neskorších predpisov ( ďalej len „ zákon“ ) vydáva podľa § 29 zákona toto rozhodnutie:\n\n Navrhovaná činnosť „Výkup železných a neželezných kovov, papiera “, ktorej účelom je výkup železných a neželezných kovov, papiera, ich dočasné zhromaždenie a uskladnenie, navrhovaná v zastavanom území Mesta Kremnica, na pozemkoch C KN par. č. 1878/1, 1878/2 a 1878/4\n sa nebude posudzovať \n\npodľa zákona.",
      "Pre uvedenú činnosť je možné požiadať o povolenie podľa osobitných predpisov.",
      "Pre realizáciu sa navrhuje variant č.",
      "A pre ktorý sa stanovujú tieto podmienky.",
      "Pri príprave dokumentácie stavby k stavebnému konaniu a v procese konania o povolení činnosti podľa osobitných predpisov je potrebné zohľadniť v plnej miere rešpektovať pripomienky, ktoré vyplynuli zo stanovísk doručených k zámeru a z opatrení navrhnutých v zámere: \n\n1. Dodržať a realizovať opatrenia na zmiernenie nepriaznivých vplyvov navrhovanej činnosti na životné prostredie počas prevádzky navrhnuté v kapitole 10. Zámeru.",
      "2. V žiadosti o vydanie súhlasu povoľujúcim orgánom stanoviť kapacitu zariadenia a vymedziť jednotlivé druhy odpadov podľa katalógových čísiel s ktorými bude nakladané v navrhovanom zariadení v zmysle platných právnych predpisov.",
      "3. Prevádzkou činnosti nesmú byť prekročené prípustné hodnoty určujúcich veličín hluku vo vonkajšom prostredí uvedené vo Vyhláške MZ SR č.549/2007 Z. z., \n\n4. Pri realizácií zámeru a prevádzkovaní navrhovanej činnosti vykonať opatrenia na ochranu existujúcich drevín na pozemku par. č. 1878//4 v zmysle § 47 zákona č. 543/2002 Z. z. o ochrane prírody a krajiny.",
      "Odôvodnenie\n\n Navrhovateľ, Anita Hrmová – AEM zberné suroviny, Veternícka 169/66, 967 01 Kremnica, IČO 00321125 požiadala dňa 17.04.2015 Okresný úrad Žiar nad Hronom, odbor starostlivosti o životné prostredie, o prerokovania zámeru „ Výkup a skladovanie železných a neželezných kovov, papiera “ v k. ú. Kremnica na pozemkoch parcelné č. 1878/1, 1878/2\na 1878/4.",
      "Nakoľko predmetné podanie nespĺňalo náležitosti podľa ustanovení § 22 ods. 3 a 4 zákona, príslušný orgán vyzval navrhovateľa o doplnenie zámeru činnosti v súlade s ustanoveniam § 22 ods. 3 a 4 zákona a nakoľko zámer činnosti nebol doplnený v stanovenom termíne, príslušný orgán rozhodnutím pod č. s. : OU-ZH-OSZP- 2015/004740 ( č. záznamu: 2015/0018401 ) dňa 02.06.2015 v danej veci prerušil správne konanie.",
      "Dňa 29.06.2015 navrhovateľ doplnil zámer činnosti v súlade s ustanoveniami § 22 zákona a požiadal o vykonanie zisťovacieho konania podľa zákona.",
      "Navrhovaná činnosť, ktorú vypracoval Ing. Vladimír Slančík, Jabloňová 1355/16, 960 01 Zvolen v 06/2015 je podľa prílohy č. 8 k zákona je zaradená do kapitoly 9. Infraštruktúra, pol. č. 10 Zhromažďovanie odpadov zo železných kovov, z neželezných kovov alebo starých vozidiel v časti B zisťovacie konanie je bez limitu v zmysle § 18 ods. 2 písm. b) zákona o posudzovaní podlieha zisťovaciemu konaniu podľa § 29 zákona.",
      "Poučenie\n\n\nProti tomuto rozhodnutiu možno podať odvolanie podľa § 53 a § 54 zákona č. 71/1967 Zb. o správnom konaní (správny poriadok) v znení neskorších predpisov na Okresnom úrade Žiar \n\nnad Hronom, odbor starostlivosti o životné prostredie v lehote do 15 dní odo dňa doručenia \nrozhodnutia účastníkovi konania.",
      "V prípade verejnosti podľa § 24 ods. 4 zákona sa za deň doručenia rozhodnutia považuje 15. deň zverejnenia rozhodnutia vydaného v zisťovacom konaní podľa § 29 ods. 15 zákona.",
      "Toto rozhodnutie je preskúmateľné súdom podľa zákona č. 99/1963 Zb. Občiansky súdny poriadok v znení neskorších predpisov až po vyčerpaní riadnych opravných prostriedkov.",
      "Ing. Dušan Berkeš \n\n\n\n \n\n vedúci odboru\nDoručuje sa:\n\n1. Anita Hrmová – AEM zberné suroviny, Veternícka 169/66, 967 01 Kremnica \n2. Ministerstvo životného prostredia SR, Nám. Ľ. Štúra 1, 827 15 Bratislava \n3. Mesto Kremnica, Štefánikovo námestie 1/1, 967 01 Kremnica \n4. Krajský pamiatkový úrad Banská Bystrica, Lazovná ulica č. 8, 975 65 Banská Bystrica \n\n5. Obvodný banský úrad Banská Bystrica, ul. 9. Mája 2, 975 65 Banská Bystrica\n6. Regionálny úrad verejného zdravotníctva, Sládkovičova 484/9,965 24 Žiar nad Hronom \n\n7. Okresné riaditeľstvo hasičského a záchranného zboru v Žiari nad Hronom, Ul. \n\n Priemyselná 12, 965 01 Žiar nad Hronom \n\n8. Okresný úrad Žiar nad Hronom, odbor krízového riadenia, Nám. M. Slovenskej č.8, 965 01\n\n Žiar nad Hronom \n9. Okresný úrad Žiar nad Hronom, pozemkový a lesný odbor, Nám. M. Slovenskej č.8,\n\n 965 01 Žiar nad Hronom\n\n10. Okresný úrad Žiar nad Hronom, odbor starostlivosti o životné prostredie - štátna vodná \n správa, štátna správa ochrany prírody a krajiny, štátna správa odpadového hospodárstva, \n štátna správa ochrany ovzdušia \n11. Združenie domových samospráv, P.O.BOX 218, 850 00 Bratislava - Petržalka");

  private static final List<String> tokens1 = Arrays
      .asList("ROZHODNUTIE", "Okresný", "úrad", "Žiar", "nad", "Hronom", ",", "odbor", "starostlivosti", "o", "životné",
          "prostredie", ",", "ako", "príslušný", "orgán", "štátnej", "správy", "na", "úseku", "posudzovania", "vplyvov",
          "na", "životné", "prostredie", "podľa", "§", "56", "písm.", "b)", "zákona", "č.", "24/2006", "Z.",
          "z.",
          "o", "posudzovaní", "vplyvov", "na", "životné", "prostredie", "a", "o", "zmene", "a", "doplnení",
          "niektorých", "zákonov", "v", "znení", "neskorších", "predpisov", ",", "v", "súlade", "s", "§", "2", "ods.",
          "3", ",", "ods.", "6", "a", "§", "3", "ods.", "1", "písm.", "e)", "zákona", "č.", "180/2013", "o",
          "organizovaní", "miestnej", "štátnej", "správy", "a", "o", "zmene", "a", "doplnení", "niektorých", "zákonov",
          "a", "podľa", "§", "5", "ods.", "1", "zákona", "NR", "SR", "č.", "525/2003", "Z.", "z.", "o", "štátnej",
          "správe", "starostlivosti", "o", "životné", "prostredie", "v", "znení", "neskorších", "predpisov", ",", "ako",
          "príslušný", "orgán", "štátnej", "správy", "na", "základe", "predloženého", "zámeru", "navrhovanej",
          "činnosti", "``", "Výkup", "železných", "a", "neželezných", "kovov", ",", "papiera", "``", ",", "ktorý",
          "predložila", "navrhovateľka", "Anita", "Hrmová", "--", "AEM", "zberné", "suroviny", ",", "Veternícka",
          "169/66", ",", "967", "01", "Kremnica", ",", "IČO", "00321125", ",", "vykonal", "zisťovacie", "konanie",
          "podľa", "§", "29", "ods.", "1", "zákona", "NR", "SR", "č.", "24/2006", "Z.", "z.", "o", "posudzovaní",
          "vplyvov", "na", "životné", "prostredie", "v", "znení", "neskorších", "predpisov", "-LRB-", "ďalej", "len",
          "``", "zákon", "``", "-RRB-", "vydáva", "podľa", "§", "29", "zákona", "toto", "rozhodnutie", ":",
          "Navrhovaná", "činnosť",
          "``", "Výkup", "železných", "a", "neželezných", "kovov", ",", "papiera", "``", ",", "ktorej", "účelom", "je",
          "výkup", "železných", "a", "neželezných", "kovov", ",", "papiera", ",", "ich", "dočasné", "zhromaždenie", "a",
          "uskladnenie", ",", "navrhovaná", "v", "zastavanom", "území", "Mesta", "Kremnica", ",", "na", "pozemkoch",
          "C", "KN", "par.", "č.", "1878/1", ",", "1878/2", "a", "1878/4", "sa", "nebude", "posudzovať", "podľa",
          "zákona", ".", "Pre", "uvedenú", "činnosť", "je", "možné", "požiadať", "o", "povolenie", "podľa",
          "osobitných", "predpisov", ".", "Pre", "realizáciu", "sa", "navrhuje", "variant", "č", ".", "A", "pre",
          "ktorý", "sa", "stanovujú", "tieto", "podmienky", ".", "Pri", "príprave", "dokumentácie", "stavby", "k",
          "stavebnému", "konaniu", "a", "v", "procese", "konania", "o", "povolení", "činnosti", "podľa", "osobitných",
          "predpisov", "je", "potrebné", "zohľadniť", "v", "plnej", "miere", "rešpektovať", "pripomienky", ",", "ktoré",
          "vyplynuli", "zo", "stanovísk", "doručených", "k", "zámeru", "a", "z", "opatrení", "navrhnutých", "v",
          "zámere", ":", "1.", "Dodržať", "a", "realizovať", "opatrenia", "na", "zmiernenie", "nepriaznivých",
          "vplyvov", "navrhovanej", "činnosti", "na", "životné", "prostredie", "počas", "prevádzky", "navrhnuté", "v",
          "kapitole", "10.", "Zámeru", ".", "2.", "V", "žiadosti", "o", "vydanie", "súhlasu", "povoľujúcim", "orgánom",
          "stanoviť", "kapacitu", "zariadenia", "a", "vymedziť", "jednotlivé", "druhy", "odpadov", "podľa",
          "katalógových", "čísiel", "s", "ktorými", "bude", "nakladané", "v", "navrhovanom", "zariadení", "v", "zmysle",
          "platných", "právnych", "predpisov", ".", "3.", "Prevádzkou", "činnosti", "nesmú", "byť", "prekročené",
          "prípustné", "hodnoty", "určujúcich", "veličín", "hluku", "vo", "vonkajšom", "prostredí", "uvedené", "vo",
          "Vyhláške", "MZ", "SR", "č.", "549/2007", "Z.", "z.", ",", "4.", "Pri", "realizácií", "zámeru", "a",
          "prevádzkovaní", "navrhovanej", "činnosti", "vykonať", "opatrenia", "na", "ochranu", "existujúcich", "drevín",
          "na", "pozemku", "par.", "č.", "1878", "/", "/", "4", "v", "zmysle", "§", "47", "zákona", "č.", "543/2002",
          "Z.", "z.", "o", "ochrane", "prírody", "a", "krajiny", ".", "Odôvodnenie", "Navrhovateľ", ",", "Anita",
          "Hrmová",
          "--", "AEM", "zberné", "suroviny", ",", "Veternícka", "169/66", ",", "967", "01", "Kremnica", ",", "IČO",
          "00321125", "požiadala", "dňa", "17.04.2015", "Okresný", "úrad", "Žiar", "nad", "Hronom", ",", "odbor",
          "starostlivosti", "o", "životné", "prostredie", ",", "o", "prerokovania", "zámeru", "``", "Výkup", "a",
          "skladovanie", "železných", "a", "neželezných", "kovov", ",", "papiera", "``", "v", "k.", "ú.", "Kremnica",
          "na", "pozemkoch", "parcelné", "č.", "1878/1", ",", "1878/2", "a", "1878/4", ".", "Nakoľko", "predmetné",
          "podanie", "nespĺňalo", "náležitosti", "podľa", "ustanovení", "§", "22", "ods.", "3", "a", "4", "zákona", ",",
          "príslušný", "orgán", "vyzval", "navrhovateľa", "o", "doplnenie", "zámeru", "činnosti", "v", "súlade", "s",
          "ustanoveniam", "§", "22", "ods.", "3", "a", "4", "zákona", "a", "nakoľko", "zámer", "činnosti", "nebol",
          "doplnený", "v", "stanovenom", "termíne", ",", "príslušný", "orgán", "rozhodnutím", "pod", "č.", "s.", ":",
          "OU-ZH-OSZP", "-", "2015/004740", "-LRB-", "č.", "záznamu", ":", "2015/0018401", "-RRB-", "dňa", "02.06.2015",
          "v", "danej", "veci", "prerušil", "správne", "konanie", ".", "Dňa", "29.06.2015", "navrhovateľ", "doplnil",
          "zámer", "činnosti", "v", "súlade", "s", "ustanoveniami", "§", "22", "zákona", "a", "požiadal", "o",
          "vykonanie", "zisťovacieho", "konania", "podľa", "zákona", ".", "Navrhovaná", "činnosť", ",", "ktorú",
          "vypracoval", "Ing.", "Vladimír", "Slančík", ",", "Jabloňová", "1355/16", ",", "960", "01", "Zvolen", "v",
          "06/2015", "je", "podľa", "prílohy", "č.", "8", "k", "zákona", "je", "zaradená", "do", "kapitoly", "9.",
          "Infraštruktúra", ",", "pol.", "č.", "10", "Zhromažďovanie", "odpadov", "zo", "železných", "kovov", ",", "z",
          "neželezných", "kovov", "alebo", "starých", "vozidiel", "v", "časti", "B", "zisťovacie", "konanie", "je",
          "bez", "limitu", "v", "zmysle", "§", "18", "ods.", "2", "písm.", "b)", "zákona", "o", "posudzovaní",
          "podlieha", "zisťovaciemu", "konaniu", "podľa", "§", "29", "zákona", ".", "Poučenie", "Proti", "tomuto",
          "rozhodnutiu", "možno", "podať", "odvolanie", "podľa", "§", "53", "a", "§", "54", "zákona", "č.", "71/1967",
          "Zb.", "o", "správnom", "konaní", "-LRB-", "správny", "poriadok", "-RRB-", "v", "znení", "neskorších",
          "predpisov", "na", "Okresnom", "úrade", "Žiar", "nad", "Hronom", ",", "odbor", "starostlivosti", "o",
          "životné", "prostredie", "v", "lehote", "do", "15", "dní", "odo", "dňa", "doručenia", "rozhodnutia",
          "účastníkovi", "konania", ".", "V", "prípade", "verejnosti", "podľa", "§", "24", "ods.", "4", "zákona", "sa",
          "za", "deň", "doručenia", "rozhodnutia", "považuje", "15.", "deň", "zverejnenia", "rozhodnutia", "vydaného",
          "v", "zisťovacom", "konaní", "podľa", "§", "29", "ods.", "15", "zákona", ".", "Toto", "rozhodnutie", "je",
          "preskúmateľné", "súdom", "podľa", "zákona", "č.", "99/1963", "Zb.", "Občiansky", "súdny", "poriadok", "v",
          "znení", "neskorších", "predpisov", "až", "po", "vyčerpaní", "riadnych", "opravných", "prostriedkov", ".",
          "Ing.", "Dušan", "Berkeš", "vedúci", "odboru", "Doručuje", "sa", ":", "1.", "Anita", "Hrmová", "--", "AEM",
          "zberné", "suroviny", ",", "Veternícka", "169/66", ",", "967", "01", "Kremnica", "2.", "Ministerstvo",
          "životného", "prostredia", "SR", ",", "Nám.", "Ľ.", "Štúra", "1", ",", "827", "15", "Bratislava", "3.",
          "Mesto", "Kremnica", ",", "Štefánikovo", "námestie", "1/1", ",", "967", "01", "Kremnica", "4.", "Krajský",
          "pamiatkový", "úrad", "Banská", "Bystrica", ",", "Lazovná", "ulica", "č.", "8", ",", "975", "65", "Banská",
          "Bystrica", "5.", "Obvodný", "banský", "úrad", "Banská", "Bystrica", ",", "ul.", "9.", "Mája", "2", ",",
          "975", "65", "Banská", "Bystrica", "6.", "Regionálny", "úrad", "verejného", "zdravotníctva", ",",
          "Sládkovičova", "484/9", ",965", "24", "Žiar", "nad", "Hronom", "7.", "Okresné", "riaditeľstvo", "hasičského",
          "a", "záchranného", "zboru", "v", "Žiari", "nad", "Hronom", ",", "Ul.", "Priemyselná", "12", ",", "965", "01",
          "Žiar", "nad", "Hronom", "8.", "Okresný", "úrad", "Žiar", "nad", "Hronom", ",", "odbor", "krízového",
          "riadenia", ",", "Nám.", "M.", "Slovenskej", "č.", "8", ",", "965", "01", "Žiar", "nad", "Hronom", "9.",
          "Okresný", "úrad", "Žiar", "nad", "Hronom", ",", "pozemkový", "a", "lesný", "odbor", ",", "Nám.", "M.",
          "Slovenskej", "č.", "8", ",", "965", "01", "Žiar", "nad", "Hronom", "10.", "Okresný", "úrad", "Žiar", "nad",
          "Hronom", ",", "odbor", "starostlivosti", "o", "životné", "prostredie", "-", "štátna", "vodná", "správa", ",",
          "štátna", "správa", "ochrany", "prírody", "a", "krajiny", ",", "štátna", "správa", "odpadového",
          "hospodárstva", ",", "štátna", "správa", "ochrany", "ovzdušia", "11.", "Združenie", "domových", "samospráv",
          ",", "P.O.BOX", "218", ",", "850", "00", "Bratislava", "-", "Petržalka");

  public static final TestText text1 = new TestText(in1, sentences1, tokens1);

  private static final String in2 = "Súd:\tOkresný súd Košice I\n"
      + "Spisová značka:\t6T/81/2018\n"
      + "Identifikačné číslo súdneho spisu:\t7118015486\n"
      + "Dátum vydania rozhodnutia:\t14. 01. 2019\n"
      + "Meno a priezvisko sudcu, VSÚ:\tJUDr. Martin Baločko\n"
      + "ECLI:\tECLI:SK:OSKE1:2019:7118015486.1\n"
      + "ROZSUDOK V MENE\n"
      + "SLOVENSKEJ REPUBLIKY\n"
      + "Predseda senátu Okresného súdu Košice I JUDr. Martin Baločko v senáte zloženom z prísediacich JUDr. Jozefa Šušku a Jána Szabóa na verejnom zasadnutí konanom dňa 14.01.2018, v trestnej veci proti obv. Eugen Gál pre prečin nebezpečného vyhrážania podľa § 360 ods.1, ods. X písm.a/, písm.e/ Tr. zákona a iné po schválení dohody o vine a treste takto\n"
      + "r o z h o d o l :\n"
      + "Obvinený  :\n"
      + "Eugen Gál,   nar. 13.07.1945 v Martine, trvale bytom Uhrova č. 567/12A, Bratislava staré mesto\n"
      + "j e     v i n n ý ,   ž e\n"
      + "v M. na ul. Mäsiarskej pred barom CUBANO, dňa 01.06.2018 okolo 23.34 hod. z doposiaľ nezistených príčin, na mieste verejnosti prístupnom a v prítomnosti piatich osôb, úmyselne fyzicky napadol poškodeného Rudolfa Ursíniho, nar. 07.07.1955, trvale bytom Bratislava, ul. Kostlivého č. 5 a to tak, že pristúpil k poškodenému, pravou rukou ho chytil v oblasti krku a so slovami „zabijem ťa“ ľavou rukou vytiahol z ľavého vrecka nohavíc vyskakovací nôž, ktorý otvoril a namieril s ním smerom k telu poškodeného, čo u poškodeného vzbudilo dôvodnú obavu o jeho život, na čo poškodený svojou ľavou rukou zadržal ľavú ruku páchateľa v oblasti zápästia, následne pravou rukou zachytil čepeľ noža a pokúšal sa odtlačiť jeho ruku s nožom od svojho tela, čím došlo k zraneniu poškodeného a to k rezným ranám na palci pravej ruky s dobou liečenia do 14 dní, t e d a\n"
      + "-inému úmyselne ublížil na zdraví,\n"
      + "-inému sa vyhrážal smrťou takým spôsobom, že to mohlo vzbudiť dôvodnú obavu a čin spáchal závažnejším spôsobom konania - so zbraňou a verejne,\n"
      + "-dopustil sa slovne a fyzicky, verejne a na mieste verejnosti prístupnom hrubej neslušnosti a výtržnosti tým, že napadol iného,\n"
      + "t ý m        s p á c h a l\n"
      + "6T/81/2018-219\n"
      + "-\tprečin nebezpečného vyhrážania podľa § 360 ods. 1, ods. 2 písm. a/, písem. e/ Tr. zákona\n"
      + "-\tprečin výtržníctva podľa § 364 ods. 1 písm. a/ Tr. zákona- prečin ublíženia na zdraví podľa § 156 ods. 1 Tr. zákona\n"
      + "Z a    t o  mu   súd  ukladá\n"
      + "podľa § 360 ods. 2 Tr. zák. , § 41 ods. 1 Tr. zák. s použitím § 38 ods. 2 , § 36 písm. l/, § 37 písm. h/ Tr. zák.,  § 39 ods. 2 písm. d/, ods. 4 Tr. zák. úhrnný trest odňatia slobody vo výmere štyroch mesiacov.\n"
      + "Podľa § 49 ods. 1 písm. a/ Tr. zák. súd výkon uloženého trestu odňatia slobody podmienečne odkladá.\n"
      + "Podľa § 50 ods. 1 Tr. zák. súd určuje skúšobnú dobu v trvaní dvanásť mesiacov.\n"
      + "o d ô v o d n e n i e :\n"
      + "Dňa 11.12.2018 podal prokurátor Okresnej prokuratúry Košice I na tunajší súd obžalobu pod sp. zn. 2 Pv 220/18 v trestnej veci obvineného E. G.,  ktorú Okresný súd Košice I obdržal dňa 21.12.2018.\n"
      + "Na verejnom zasadnutí konanom dňa 14.01.2019 prokurátor súhlasil s návrhom na schválenie dohody o vine a treste pod vyššie uvedenou spisovou značkou a tak obvinený na verejnom zasadnutí po poučení o svojich právach na otázky predsedu senátu, uvedené v ustanovení §-u 333 ods. 3 písm. a/ až j/ odpovedal:\n"
      + "a/ Či rozumie podanému návrhu dohody o vine a treste, odpoveď obvineného: Áno.\n"
      + "b/ Či súhlasí, aby sa  trestná vec prejednala touto skrátenou formou, čím sa vzdáva práva na verejný súdny proces, odpoveď obvineného: Áno.\n"
      + "c/ Či rozumie, čo tvorí podstatu skutku, ktorý sa mu kladie za vinu, odpoveď obvineného: Áno.\n"
      + "d/ Či bol ako obvinený poučený o svojich právach, najmä o práve na obhajobu, či mu bola daná možnosť na slobodnú voľbu obhajcu a či sa s obhajcom mohol radiť o spôsobe obhajoby, odpoveď obvineného: Áno.\n"
      + "e/ Či rozumie podstate konania o návrhu na dohodu o vine a treste, odpoveď obvineného: Áno.\n"
      + "f/ Či rozumie právnej kvalifikácii skutku ako trestného činu, odpoveď obvineného: Áno.\n"
      + "g/ Či bol oboznámený s trestnými sadzbami, ktoré zákon ustanovuje za trestné činy kladené mu za  vinu, odpoveď obvineného: Áno.\n"
      + "h/ Či sa dobrovoľne priznal a uznal vinu za spáchaný skutok, ktorý sa v návrhu dohody o vine a treste kvalifikuje ako určitý trestný čin, odpoveď obvineného: Áno.\n"
      + "i/ Či súhlasí s navrhovaným trestom, trest prijíma a v stanovených lehotách sa podradí výkonu trestu, odpoveď obvineného: Áno.\n"
      + "j/ Či si uvedomuje, že ak súd prijme návrh na dohodu o vine a treste a vynesie rozsudok, ktorý nadobudne právoplatnosť vyhlásením, nebude možné proti tomuto rozsudku podať odvolanie, odpoveď obvineného: Áno.\n"
      + "Nakoľko obvinený odpovedal na všetky otázky „Áno“, mohol predseda senátu rozhodovať o skutku, jeho právnej kvalifikácii a primeranosti trestu. Uzavretú dohodu predseda senátu na verejnom zasadnutí vyhodnotil ako primeranú a spravodlivú, preto rozsudkom podľa § 334 ods. 4 Tr. por. predloženú dohodu schválil a rozhodol tak, ako je to uvedené vo výrokovej časti tohto rozsudku.\n"
      + "Poučenie:\n"
      + "Proti tomuto rozsudku nie je podľa § 334 ods. 4 Tr. por. prípustné odvolanie  ani dovolanie, okrem dovolania podľa § 371 ods. 1 písm. c/, ods. 2 Tr. por.\n";

  private static final List<String> sentences2 = Arrays.asList(
      "Súd:	Okresný súd Košice I\nSpisová značka:	6T/81/2018\nIdentifikačné číslo súdneho spisu:	7118015486\nDátum vydania rozhodnutia:	14. 01. 2019\nMeno a priezvisko sudcu, VSÚ:	JUDr. Martin Baločko\nECLI:	ECLI:SK:OSKE1:2019:7118015486.1\nROZSUDOK V MENE\nSLOVENSKEJ REPUBLIKY\nPredseda senátu Okresného súdu Košice I JUDr. Martin Baločko v senáte zloženom z prísediacich JUDr. Jozefa Šušku a Jána Szabóa na verejnom zasadnutí konanom dňa 14.01.2018, v trestnej veci proti obv. Eugen Gál pre prečin nebezpečného vyhrážania podľa § 360 ods.1, ods. X písm.a), písm.e) Tr. zákona a iné po schválení dohody o vine a treste takto\n\nrozhodol :\nObvinený :\nEugen Gál, nar. 13.07.1945 v Martine, trvale bytom Uhrova č. 567/12A, Bratislava staré mesto\n\nje vinný , že\n\nv M. na ul. Mäsiarskej pred barom CUBANO, dňa 01.06.2018 okolo 23.34 hod. z doposiaľ nezistených príčin, na mieste verejnosti prístupnom a v prítomnosti piatich osôb, úmyselne fyzicky napadol poškodeného Rudolfa Ursíniho, nar. 07.07.1955, trvale bytom Bratislava, ul. Kostlivého č. 5 a to tak, že pristúpil k poškodenému, pravou rukou ho chytil v oblasti krku a so slovami „zabijem ťa“ ľavou rukou vytiahol z ľavého vrecka nohavíc vyskakovací nôž, ktorý otvoril a namieril s ním smerom k telu poškodeného, čo u poškodeného vzbudilo dôvodnú obavu o jeho život, na čo poškodený svojou ľavou rukou zadržal ľavú ruku páchateľa v oblasti zápästia, následne pravou rukou zachytil čepeľ noža a pokúšal sa odtlačiť jeho ruku s nožom od svojho tela, čím došlo k zraneniu poškodeného a to k rezným ranám na palci pravej ruky s dobou liečenia do 14 dní, teda\n\n-inému úmyselne ublížil na zdraví,\n-inému sa vyhrážal smrťou takým spôsobom, že to mohlo vzbudiť dôvodnú obavu a čin spáchal závažnejším spôsobom konania - so zbraňou a verejne,\n-dopustil sa slovne a fyzicky, verejne a na mieste verejnosti prístupnom hrubej neslušnosti a výtržnosti tým, že napadol iného,\n\ntým spáchal\n\n6T/81/2018-219\n-	prečin nebezpečného vyhrážania podľa § 360 ods. 1, ods. 2 písm. a), písem.",
      "e) Tr. zákona\n-	prečin výtržníctva podľa § 364 ods. 1 písm. a) Tr. zákona- prečin ublíženia na zdraví podľa § 156 ods. 1 Tr. zákona\n\nZa to mu súd ukladá\npodľa § 360 ods. 2 Tr. zák. , § 41 ods. 1 Tr. zák. s použitím § 38 ods. 2 , § 36 písm. l), § 37 písm. h) Tr. zák., § 39 ods. 2 písm. d), ods. 4 Tr. zák. úhrnný trestodňatia slobody vo výmere štyroch mesiacov.",
      "Podľa § 49 ods. 1 písm. a) Tr. zák. súd výkon uloženého trestu odňatia slobody podmienečne odkladá.",
      "Podľa § 50 ods. 1 Tr. zák. súd určuje skúšobnú dobu v trvaní dvanásť mesiacov.",
      "odôvodnenie :\nDňa 11.12.2018 podal prokurátor Okresnej prokuratúry Košice I na tunajší súd obžalobu pod sp. zn. 2 Pv 220/18 v trestnej veci obvineného E. G., ktorú Okresný súd Košice I obdržal dňa 21.12.2018.\nNa verejnom zasadnutí konanom dňa 14.01.2019 prokurátor súhlasil s návrhom na schválenie dohody o vine a treste pod vyššie uvedenou spisovou značkou a tak obvinený na verejnom zasadnutí po poučení o svojich právach na otázky predsedu senátu, uvedené v ustanovení §-u 333 ods. 3 písm. a) až j) odpovedal:\na) Či rozumie podanému návrhu dohody o vine a treste, odpoveď obvineného: Áno.",
      "b) Či súhlasí, aby sa trestná vec prejednala touto skrátenou formou, čím sa vzdáva práva na verejný súdny proces, odpoveď obvineného: Áno.",
      "c) Či rozumie, čo tvorí podstatu skutku, ktorý sa mu kladie za vinu, odpoveď obvineného: Áno.",
      "d) Či bol ako obvinený poučený o svojich právach, najmä o práve na obhajobu, či mu bola daná možnosť na slobodnú voľbu obhajcu a či sa s obhajcom mohol radiť o spôsobe obhajoby, odpoveď obvineného: Áno.",
      "e) Či rozumie podstate konania o návrhu na dohodu o vine a treste, odpoveď obvineného: Áno.",
      "f) Či rozumie právnej kvalifikácii skutku ako trestného činu, odpoveď obvineného: Áno.",
      "g) Či bol oboznámený s trestnými sadzbami, ktoré zákon ustanovuje za trestné činy kladené mu za vinu, odpoveď obvineného: Áno.",
      "h) Či sa dobrovoľne priznal a uznal vinu za spáchaný skutok, ktorý sa v návrhu dohody o vine a treste kvalifikuje ako určitý trestný čin, odpoveď obvineného: Áno.",
      "i) Či súhlasí s navrhovaným trestom, trest prijíma a v stanovených lehotách sa podradí výkonu trestu, odpoveď obvineného: Áno.",
      "j) Či si uvedomuje, že ak súd prijme návrh na dohodu o vine a treste a vynesie rozsudok, ktorý nadobudne právoplatnosť vyhlásením, nebude možné proti tomuto rozsudku podať odvolanie, odpoveď obvineného: Áno.",
      "Nakoľko obvinený odpovedal na všetky otázky „Áno“, mohol predseda senátu rozhodovať o skutku, jeho právnej kvalifikácii a primeranosti trestu.",
      "Uzavretú dohodu predseda senátu na verejnom zasadnutí vyhodnotil ako primeranú a spravodlivú, preto rozsudkom podľa § 334 ods. 4 Tr. por. predloženú dohodu schválil a rozhodol tak, ako je to uvedené vo výrokovej časti tohto rozsudku.",
      "Poučenie:\nProti tomuto rozsudku nie je podľa § 334 ods. 4 Tr. por. prípustné odvolanie ani dovolanie, okrem dovolania podľa § 371 ods. 1 písm. c), ods. 2 Tr. por.");

  private static final List<String> tokens2 = Arrays
      .asList("Súd", ":", "Okresný", "súd", "Košice", "I", "Spisová", "značka", ":", "6T/81/2018", "Identifikačné",
          "číslo", "súdneho", "spisu", ":", "7118015486", "Dátum", "vydania", "rozhodnutia", ":", "14. 01. 2019",
          "Meno", "a", "priezvisko", "sudcu", ",", "VSÚ", ":", "JUDr.", "Martin", "Baločko", "ECLI", ":", "ECLI", ":",
          "SK", ":", "OSKE1", ":", "2019:7118015486.1", "ROZSUDOK", "V", "MENE", "SLOVENSKEJ", "REPUBLIKY", "Predseda",
          "senátu", "Okresného", "súdu", "Košice", "I", "JUDr.", "Martin", "Baločko", "v", "senáte", "zloženom", "z",
          "prísediacich", "JUDr.", "Jozefa", "Šušku", "a", "Jána", "Szabóa", "na", "verejnom", "zasadnutí", "konanom",
          "dňa", "14.01.2018", ",", "v", "trestnej", "veci", "proti", "obv.", "Eugen", "Gál", "pre", "prečin",
          "nebezpečného", "vyhrážania", "podľa", "§", "360", "ods.", "1", ",", "ods.", "X", "písm.", "a)", ",",
          "písm.", "e)", "Tr.", "zákona", "a", "iné", "po", "schválení", "dohody", "o", "vine", "a", "treste",
          "takto", "rozhodol", ":", "Obvinený", ":", "Eugen", "Gál", ",", "nar.", "13.07.1945", "v", "Martine", ",",
          "trvale", "bytom", "Uhrova", "č.", "567/12A", ",", "Bratislava", "staré", "mesto", "je", "vinný", ",", "že",
          "v", "M.", "na", "ul.", "Mäsiarskej", "pred", "barom", "CUBANO", ",", "dňa", "01.06.2018", "okolo", "23.34",
          "hod.", "z", "doposiaľ", "nezistených", "príčin", ",", "na", "mieste", "verejnosti", "prístupnom", "a", "v",
          "prítomnosti", "piatich", "osôb", ",", "úmyselne", "fyzicky", "napadol", "poškodeného", "Rudolfa", "Ursíniho",
          ",", "nar.", "07.07.1955", ",", "trvale", "bytom", "Bratislava", ",", "ul.", "Kostlivého", "č.", "5", "a",
          "to", "tak", ",", "že", "pristúpil", "k", "poškodenému", ",", "pravou", "rukou", "ho", "chytil", "v",
          "oblasti", "krku", "a", "so", "slovami", "``", "zabijem", "ťa", "``", "ľavou", "rukou", "vytiahol", "z",
          "ľavého", "vrecka", "nohavíc", "vyskakovací", "nôž", ",", "ktorý", "otvoril", "a", "namieril", "s", "ním",
          "smerom", "k", "telu", "poškodeného", ",", "čo", "u", "poškodeného", "vzbudilo", "dôvodnú", "obavu", "o",
          "jeho", "život", ",", "na", "čo", "poškodený", "svojou", "ľavou", "rukou", "zadržal", "ľavú", "ruku",
          "páchateľa", "v", "oblasti", "zápästia", ",", "následne", "pravou", "rukou", "zachytil", "čepeľ", "noža", "a",
          "pokúšal", "sa", "odtlačiť", "jeho", "ruku", "s", "nožom", "od", "svojho", "tela", ",", "čím", "došlo", "k",
          "zraneniu", "poškodeného", "a", "to", "k", "rezným", "ranám", "na", "palci", "pravej", "ruky", "s", "dobou",
          "liečenia", "do", "14", "dní", ",", "teda", "-", "inému", "úmyselne", "ublížil", "na", "zdraví", ",", "-",
          "inému", "sa", "vyhrážal", "smrťou", "takým", "spôsobom", ",", "že", "to", "mohlo", "vzbudiť", "dôvodnú",
          "obavu", "a", "čin", "spáchal", "závažnejším", "spôsobom", "konania", "-", "so", "zbraňou", "a", "verejne",
          ",", "-", "dopustil", "sa", "slovne", "a", "fyzicky", ",", "verejne", "a", "na", "mieste", "verejnosti",
          "prístupnom", "hrubej", "neslušnosti", "a", "výtržnosti", "tým", ",", "že", "napadol", "iného", ",", "tým",
          "spáchal", "6T/81/2018", "-", "219", "-", "prečin", "nebezpečného", "vyhrážania", "podľa", "§", "360", "ods.",
          "1", ",", "ods.", "2", "písm.", "a)", ",", "písem", ".", "e)", "Tr.", "zákona", "-", "prečin",
          "výtržníctva", "podľa", "§", "364", "ods.", "1", "písm.", "a)", "Tr.", "zákona", "-", "prečin",
          "ublíženia", "na", "zdraví", "podľa", "§", "156", "ods.", "1", "Tr.", "zákona", "Za", "to", "mu", "súd",
          "ukladá", "podľa", "§", "360", "ods.", "2", "Tr.", "zák.", ",", "§", "41", "ods.", "1", "Tr.", "zák.", "s",
          "použitím", "§", "38", "ods.", "2", ",", "§", "36", "písm.", "l)", ",", "§", "37", "písm.", "h)",
          "Tr.", "zák.", ",", "§", "39", "ods.", "2", "písm.", "d)", ",", "ods.", "4", "Tr.", "zák.", "úhrnný",
          "trestodňatia", "slobody", "vo", "výmere", "štyroch", "mesiacov", ".", "Podľa", "§", "49", "ods.", "1",
          "písm.", "a)", "Tr.", "zák.", "súd", "výkon", "uloženého", "trestu", "odňatia", "slobody", "podmienečne",
          "odkladá", ".", "Podľa", "§", "50", "ods.", "1", "Tr.", "zák.", "súd", "určuje", "skúšobnú", "dobu", "v",
          "trvaní", "dvanásť", "mesiacov", ".", "odôvodnenie", ":", "Dňa", "11.12.2018", "podal", "prokurátor",
          "Okresnej", "prokuratúry", "Košice", "I", "na", "tunajší", "súd", "obžalobu", "pod", "sp.", "zn.", "2", "Pv",
          "220/18", "v", "trestnej", "veci", "obvineného", "E.", "G.", ",", "ktorú", "Okresný", "súd", "Košice", "I",
          "obdržal", "dňa", "21.12.2018.", "Na", "verejnom", "zasadnutí", "konanom", "dňa", "14.01.2019", "prokurátor",
          "súhlasil", "s", "návrhom", "na", "schválenie", "dohody", "o", "vine", "a", "treste", "pod", "vyššie",
          "uvedenou", "spisovou", "značkou", "a", "tak", "obvinený", "na", "verejnom", "zasadnutí", "po", "poučení",
          "o", "svojich", "právach", "na", "otázky", "predsedu", "senátu", ",", "uvedené", "v", "ustanovení", "§", "-",
          "u", "333", "ods.", "3", "písm.", "a)", "až", "j)", "odpovedal", ":", "a)", "Či", "rozumie",
          "podanému", "návrhu", "dohody", "o", "vine", "a", "treste", ",", "odpoveď", "obvineného", ":", "Áno", ".",
          "b)", "Či", "súhlasí", ",", "aby", "sa", "trestná", "vec", "prejednala", "touto", "skrátenou", "formou",
          ",", "čím", "sa", "vzdáva", "práva", "na", "verejný", "súdny", "proces", ",", "odpoveď", "obvineného", ":",
          "Áno", ".", "c)", "Či", "rozumie", ",", "čo", "tvorí", "podstatu", "skutku", ",", "ktorý", "sa", "mu",
          "kladie", "za", "vinu", ",", "odpoveď", "obvineného", ":", "Áno", ".", "d)", "Či", "bol", "ako",
          "obvinený", "poučený", "o", "svojich", "právach", ",", "najmä", "o", "práve", "na", "obhajobu", ",", "či",
          "mu", "bola", "daná", "možnosť", "na", "slobodnú", "voľbu", "obhajcu", "a", "či", "sa", "s", "obhajcom",
          "mohol", "radiť", "o", "spôsobe", "obhajoby", ",", "odpoveď", "obvineného", ":", "Áno", ".", "e)", "Či",
          "rozumie", "podstate", "konania", "o", "návrhu", "na", "dohodu", "o", "vine", "a", "treste", ",", "odpoveď",
          "obvineného", ":", "Áno", ".", "f)", "Či", "rozumie", "právnej", "kvalifikácii", "skutku", "ako",
          "trestného", "činu", ",", "odpoveď", "obvineného", ":", "Áno", ".", "g)", "Či", "bol", "oboznámený", "s",
          "trestnými", "sadzbami", ",", "ktoré", "zákon", "ustanovuje", "za", "trestné", "činy", "kladené", "mu", "za",
          "vinu", ",", "odpoveď", "obvineného", ":", "Áno", ".", "h)", "Či", "sa", "dobrovoľne", "priznal", "a",
          "uznal", "vinu", "za", "spáchaný", "skutok", ",", "ktorý", "sa", "v", "návrhu", "dohody", "o", "vine", "a",
          "treste", "kvalifikuje", "ako", "určitý", "trestný", "čin", ",", "odpoveď", "obvineného", ":", "Áno", ".",
          "i)", "Či", "súhlasí", "s", "navrhovaným", "trestom", ",", "trest", "prijíma", "a", "v", "stanovených",
          "lehotách", "sa", "podradí", "výkonu", "trestu", ",", "odpoveď", "obvineného", ":", "Áno", ".", "j)",
          "Či", "si", "uvedomuje", ",", "že", "ak", "súd", "prijme", "návrh", "na", "dohodu", "o", "vine", "a",
          "treste", "a", "vynesie", "rozsudok", ",", "ktorý", "nadobudne", "právoplatnosť", "vyhlásením", ",", "nebude",
          "možné", "proti", "tomuto", "rozsudku", "podať", "odvolanie", ",", "odpoveď", "obvineného", ":", "Áno", ".",
          "Nakoľko", "obvinený", "odpovedal", "na", "všetky", "otázky", "``", "Áno", "``", ",", "mohol", "predseda",
          "senátu", "rozhodovať", "o", "skutku", ",", "jeho", "právnej", "kvalifikácii", "a", "primeranosti", "trestu",
          ".", "Uzavretú", "dohodu", "predseda", "senátu", "na", "verejnom", "zasadnutí", "vyhodnotil", "ako",
          "primeranú", "a", "spravodlivú", ",", "preto", "rozsudkom", "podľa", "§", "334", "ods.", "4", "Tr.", "por.",
          "predloženú", "dohodu", "schválil", "a", "rozhodol", "tak", ",", "ako", "je", "to", "uvedené", "vo",
          "výrokovej", "časti", "tohto", "rozsudku", ".", "Poučenie", ":", "Proti", "tomuto", "rozsudku", "nie", "je",
          "podľa", "§", "334", "ods.", "4", "Tr.", "por.", "prípustné", "odvolanie", "ani", "dovolanie", ",", "okrem",
          "dovolania", "podľa", "§", "371", "ods.", "1", "písm.", "c)", ",", "ods.", "2", "Tr.", "por.", ".");

  public static final TestText text2 = new TestText(in2, sentences2, tokens2);

  public static void findFirstWrongString(List<String> expected, List<String> actual) {
    for (int i = 0; i < expected.size(); i++) {
      if (!expected.get(i).equals(actual.get(i))) {
        System.out.println(
            "on place " + i + " wrong token '" +actual.get(i) + "' expected '" + expected.get(i)
                + "'");
      }
    }
  }

}
