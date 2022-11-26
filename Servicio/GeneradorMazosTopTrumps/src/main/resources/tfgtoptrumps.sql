CREATE DATABASE  IF NOT EXISTS `tfgtoptrumps` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `tfgtoptrumps`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: tfgtoptrumps
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `card_categories`
--

DROP TABLE IF EXISTS `card_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `card_categories` (
  `idCard` int NOT NULL,
  `category` varchar(15) NOT NULL,
  `value` double DEFAULT NULL,
  `idCategoryCard` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idCategoryCard`),
  UNIQUE KEY `idCategoryCard_UNIQUE` (`idCategoryCard`),
  UNIQUE KEY `unique_category_card` (`idCard`,`category`)
) ENGINE=InnoDB AUTO_INCREMENT=827 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card_categories`
--

LOCK TABLES `card_categories` WRITE;
/*!40000 ALTER TABLE `card_categories` DISABLE KEYS */;
INSERT INTO `card_categories` VALUES (207,'Tamaño',8.894878560853709,545),(207,'Rareza',90.65634579082294,546),(207,'Dócil',85.5687706314769,547),(207,'Agilidad',22.297831120738326,548),(207,'Fuerza',49.700117563278084,549),(207,'Crítico',3.9962073405664444,550),(208,'Fuerza',20.076512801893948,551),(208,'Agilidad',54.51196446867919,552),(208,'Rareza',99.12708733616736,553),(208,'Tamaño',97.38035360640585,554),(208,'Crítico',50.78688417324263,555),(208,'Dócil',5.446513354653264,556),(209,'Fuerza',17.8564560468945,557),(209,'Rareza',20.270058234022798,558),(209,'Crítico',96.59528444168104,559),(209,'Agilidad',6.84079169732528,560),(209,'Tamaño',67.50358922216861,561),(209,'Dócil',68.4578263636736,562),(210,'Rareza',56.49928266662076,563),(210,'Fuerza',88.11440311607713,564),(210,'Agilidad',45.10332299782655,565),(210,'Tamaño',76.58639041334705,566),(210,'Crítico',45.43379646988937,567),(210,'Dócil',95.14872133845087,568),(211,'Agilidad',30.36027416186481,569),(211,'Tamaño',52.60436235652137,570),(211,'Fuerza',93.37453075471544,571),(211,'Tenebrosidad',95.35189023490119,572),(211,'Popularidad',75.0044310985366,573),(211,'Maldad',99.2106650887117,574),(212,'Tamaño',12.639384430350411,575),(212,'Fuerza',19.746507174266434,576),(212,'Agilidad',50.99611609299767,577),(212,'Tenebrosidad',97.61547893624416,578),(212,'Popularidad',25.46132598472141,579),(212,'Maldad',31.855402278762007,580),(213,'Fuerza',97.60965942106009,581),(213,'Tamaño',62.042843424339424,582),(213,'Tenebrosidad',87.7657099902015,583),(213,'Agilidad',26.80422648107008,584),(213,'Popularidad',65.08265865769529,585),(213,'Maldad',71.19211552025001,586),(216,'Velocidad',72.39348184445592,591),(216,'Fuerza',33.874495201181915,592),(216,'Hermosura',20.688561320951397,593),(216,'Habilidad',78.12937599209586,594),(216,'Maña',41.36030863853114,595),(216,'Inteligencia',1.0000210052023792,596),(217,'Fuerza',73.33106063177547,597),(217,'Velocidad',93.56092163078824,598),(217,'Hermosura',57.67752610966371,599),(217,'Habilidad',65.93851537112994,600),(217,'Maña',67.32406705616444,601),(217,'Inteligencia',81.97594163180341,602),(218,'Fuerza',44.694539252525004,603),(218,'Habilidad',70.51768310078583,604),(218,'Velocidad',56.88006672252469,605),(218,'Maña',19.026506255556605,606),(218,'Hermosura',59.35199484621564,607),(218,'Inteligencia',16.66741566679753,608),(219,'Labia',13.385741941388225,609),(219,'Preparación',24.363358691216682,610),(219,'Gracia',5.502353700856595,611),(219,'Inteligencia',39.13527663500847,612),(219,'Horas de sueño',93.97261004155921,613),(219,'Escritura',3.0319920680519132,614),(220,'Gracia',49.35636288778681,615),(220,'Inteligencia',78.87804522369768,616),(220,'Horas de sueño',61.823784034212494,617),(220,'Preparación',85.75494366301403,618),(220,'Labia',44.58899044186336,619),(220,'Escritura',87.8380260294441,620),(221,'Gracia',54.32711137858733,621),(221,'Preparación',59.709425956153495,622),(221,'Inteligencia',37.79486606337969,623),(221,'Labia',2.032673245245416,624),(221,'Horas de sueño',74.00336737510243,625),(221,'Escritura',81.18590044922854,626),(222,'Gracia',45.1580357457312,627),(222,'Labia',28.625288226629852,628),(222,'Escritura',34.24946211707378,629),(222,'Inteligencia',68.87538897928764,630),(222,'Preparación',77.8429692469654,631),(222,'Horas de sueño',39.80580488912417,632),(223,'Tamaño',61.87143746194593,633),(223,'Tenebrosidad',79.71679179625836,634),(223,'Asesinatos',82.15864544773126,635),(223,'Fuerza',26.0272377522774,636),(223,'Popularidad',11.392033382262817,637),(223,'Maldad',51.04375376631871,638),(224,'Tenebrosidad',80.22626668728199,639),(224,'Tamaño',91.13886406256582,640),(224,'Fuerza',66.09027778739633,641),(224,'Popularidad',58.386638083714324,642),(224,'Maldad',73.54247165393302,643),(224,'Asesinatos',21.707668155892925,644),(225,'Esperanza',98.70433384140676,645),(225,'Valentía',60.2861237194345,646),(225,'Ímpetu',14.845717855929026,647),(225,'Empatía',73.24053430112494,648),(225,'Entrega',20.22325531838923,649),(225,'Resistencia',16.30116779037226,650),(226,'Resistencia',20.6499136165898,651),(226,'Esperanza',46.36422023831452,652),(226,'Ímpetu',69.4799555702035,653),(226,'Valentía',6.479147709387984,654),(226,'Empatía',65.87691451518131,655),(226,'Entrega',71.16968618530993,656),(227,'Resistencia',25.2512969897574,657),(227,'Empatía',62.19467447449827,658),(227,'Esperanza',67.33214766183026,659),(227,'Ímpetu',41.86653172501173,660),(227,'Entrega',68.0246524307041,661),(227,'Valentía',7.765400951798533,662),(228,'Grosor',3.7807614162924494,663),(228,'Altura',84.66067308888373,664),(228,'Comicidad',1.574063886675452,665),(228,'Dinero',14.203605089196458,667),(228,'Edad',68.46943297817823,668),(229,'Comicidad',65.80742262625272,669),(229,'Altura',59.72226665309388,670),(229,'Grosor',94.41090095177768,671),(229,'Edad',86.00496896743181,673),(229,'Dinero',17.664829266189564,674),(230,'Comicidad',28.597511596733824,676),(230,'Altura',61.44214488636983,677),(230,'Grosor',75.80199138887069,678),(230,'Edad',25.098788690479083,679),(230,'Dinero',4.832502859826551,680),(231,'Agilidad',7.045645072022246,681),(231,'Habilidad',24.60132112897807,682),(231,'Tamaño',36.47044239227248,683),(231,'Poder',5.665164721055822,684),(231,'Valentía',49.920642197006515,685),(231,'Fuerza',48.03087414296181,686),(232,'Fuerza',3.347086691982958,687),(232,'Poder',2.6700903863087504,688),(232,'Habilidad',15.151324245187876,689),(232,'Agilidad',97.84587175296095,690),(232,'Tamaño',25.453230496141543,691),(232,'Valentía',71.00406605412388,692),(233,'Fuerza',61.70645697088164,693),(233,'Poder',46.93834348129201,694),(233,'Agilidad',12.735621044727704,695),(233,'Habilidad',38.516610742211896,696),(233,'Valentía',29.771676338954776,697),(233,'Tamaño',15.1515865335385,698),(234,'Depredación',27.1821635527383,699),(234,'Inteligencia',81.80909606524051,700),(234,'Tenebrosidad',82.32904764466895,701),(234,'Altura',28.37212953777545,702),(234,'Maldad',12.118334366540452,703),(234,'Fuerza',86.62973027210579,704),(235,'Altura',58.036782215027465,705),(235,'Maldad',31.17513839054589,706),(235,'Tenebrosidad',18.017101901066184,707),(235,'Inteligencia',80.15463707779845,708),(235,'Depredación',70.32604250744117,709),(235,'Fuerza',63.12153761125971,710),(236,'Altura',81.95005821029577,711),(236,'Maldad',63.745840117301285,712),(236,'Depredación',94.05838819632658,713),(236,'Tenebrosidad',74.22666801925105,714),(236,'Inteligencia',56.15730234560354,715),(236,'Fuerza',69.5239268576115,716),(237,'Altura',68.56112028516236,717),(237,'Tenebrosidad',58.3555965572543,718),(237,'Maldad',74.68554899919913,719),(237,'Depredación',12.604605578723548,720),(237,'Inteligencia',4.829818050019014,721),(237,'Fuerza',7.047295775035085,722),(241,'Fuerza',21.211875000776384,729),(241,'Tamaño',62.0043751105935,730),(241,'Agilidad',65.57674321941188,731),(241,'Lealtad',30.461636071070686,732),(241,'Bondad',75.65559162975617,733),(241,'Resistencia',22.25815938250271,734),(242,'Fuerza',21.39762223045414,735),(242,'Tamaño',23.02539125501848,736),(242,'Bondad',66.14542395525305,737),(242,'Agilidad',7.408665126102591,738),(242,'Lealtad',66.55773745790808,739),(242,'Resistencia',97.47540918279725,740),(243,'Tamaño',4.385327276282647,741),(243,'Fuerza',62.728424727849635,742),(243,'Agilidad',43.73993267306818,743),(243,'Bondad',68.4563034403817,744),(243,'Resistencia',32.329667572425905,745),(243,'Lealtad',94.36713784673466,746),(244,'Fuerza',97.61831318815672,747),(244,'Tamaño',73.22265205927125,748),(244,'Agilidad',98.85353056911418,749),(244,'Bondad',4.2012468056862,750),(244,'Lealtad',9.22814830443538,751),(244,'Resistencia',49.154288556514814,752),(245,'Fuerza',38.91885610614895,753),(245,'Tamaño',95.32909053329757,754),(245,'Agilidad',48.085048667851645,755),(245,'Bondad',87.92070719385596,756),(245,'Lealtad',63.78667047886259,757),(245,'Resistencia',81.79416250614038,758),(246,'Fuerza',16.306189400691686,759),(246,'Tamaño',96.9760551655147,760),(246,'Agilidad',92.47672795263246,761),(246,'Bondad',28.705461882505517,762),(246,'Lealtad',73.86173503306142,763),(246,'Resistencia',66.97564673854338,764),(247,'Agilidad',86.70164107869222,765),(247,'Fuerza',4.271274177019947,766),(247,'Tamaño',66.73052474406909,767),(247,'Bondad',68.60460423738456,768),(247,'Lealtad',14.316254500776068,769),(247,'Resistencia',78.544605778678,770),(248,'Fuerza',7.620627068308281,771),(248,'Tamaño',57.18262511575918,772),(248,'Bondad',26.796667786748113,773),(248,'Agilidad',9.10457188743656,774),(248,'Lealtad',90.31456333622148,775),(248,'Resistencia',36.27926002976061,776),(249,'Tamaño',1.7953459298748289,777),(249,'Fuerza',38.926138401117214,778),(249,'Agilidad',33.81379895961991,779),(249,'Bondad',87.4855682545251,780),(249,'Resistencia',60.410018048613196,781),(249,'Lealtad',82.17038310130042,782),(250,'Fuerza',30.060152537924264,783),(250,'Tamaño',65.812293585681,784),(250,'Agilidad',59.792865854695854,785),(250,'Bondad',16.62498481921949,786),(250,'Lealtad',67.42008803115803,787),(250,'Resistencia',22.62698689239363,788),(251,'Fuerza',98.57014257751187,789),(251,'Tamaño',73.1707079905396,790),(251,'Agilidad',82.25063003448278,791),(251,'Bondad',61.77414752102745,792),(251,'Lealtad',65.49728186955593,793),(251,'Resistencia',76.69911013190773,794),(252,'Fuerza',85.10542184792413,795),(252,'Tamaño',1.4312782965454147,796),(252,'Agilidad',86.44120292304353,797),(252,'Bondad',70.70628271196243,798),(252,'Lealtad',29.133478328472002,799),(252,'Resistencia',3.6093852397330295,800),(253,'Fuerza',91.35523619109513,801),(253,'Tamaño',88.92244364905696,802),(253,'Agilidad',25.717726317122487,803),(253,'Bondad',23.474367172107797,804),(253,'Lealtad',95.14664273285275,805),(253,'Resistencia',29.134809174074835,806),(254,'Fuerza',60.817045542432936,807),(254,'Agilidad',49.34440174517435,808),(254,'Tamaño',63.09021507094534,809),(254,'Bondad',83.46845333603922,810),(254,'Lealtad',38.45342707922416,811),(254,'Resistencia',67.9742817819935,812),(255,'Fuerza',19.614136061032905,813),(255,'Tamaño',59.286120593237456,814),(255,'Bondad',14.02856080372431,815),(255,'Lealtad',49.50586489773325,816),(255,'Agilidad',58.30903408018728,817),(255,'Resistencia',69.25384640339318,818);
/*!40000 ALTER TABLE `card_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cards`
--

DROP TABLE IF EXISTS `cards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cards` (
  `idCard` int NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `image` varchar(4000) DEFAULT NULL,
  `idDeck` int NOT NULL,
  PRIMARY KEY (`idCard`),
  KEY `cards_ibfk_1` (`idDeck`),
  CONSTRAINT `cards_ibfk_1` FOREIGN KEY (`idDeck`) REFERENCES `decks` (`idDeck`)
) ENGINE=InnoDB AUTO_INCREMENT=260 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cards`
--

LOCK TABLES `cards` WRITE;
/*!40000 ALTER TABLE `cards` DISABLE KEYS */;
INSERT INTO `cards` VALUES (207,'Pikachu','El pokemon más famoso de todos. Fiel compañero de Ash Ketchup, resulta ser el más adorable; pero que no os confunda su aparente aspecto inofensivo, es bastante poderoso y, con su \"impact trueno\", ha vencido numerosas veces al Team Rocket.','https://assets.stickpng.com/images/580b57fcd9996e24bc43c325.png',237),(208,'Charizard','La máxima evolución de Charmander. Es un dragón de fuego imponente y uno de los pokemons más queridos por los fans.','https://assets.pokemon.com/assets/cms2/img/pokedex/full/006.png',237),(209,'Squirtle','Quizá, en su momento, superó en popùlaridad a Pikachu con el famoso meme de \"¡Vamó a calmarnó\". Un pokemon enternecedor y graciosa, que un máxima evolución es temible.','https://images.wikidexcdn.net/mwuploads/wikidex/e/e3/latest/20160309230820/Squirtle.png',237),(210,'Dialga','Pokemon legendario muy cotizado, que nació con el videojuego de nintendo \"Pokemon edición diamante\".','https://assets.pokemon.com/assets/cms2/img/pokedex/full/483.png',237),(211,'Pennywise','El payaso más terrorífico de todos. Aparenta ser un humano psicópata disfrazado, pero esconde algo más. Es, quizás, el personaje más célebre de todos los que ha creado Stephen King. Antagonista de la novela \"It\", versionada posteriormente en numerosas películas y series, es un monstruo macro cósmico lovecraftniano.','https://indiehoy.com/wp-content/uploads/2022/03/it-pelicula.jpg',238),(212,'Danny Torrance','Hijo de Jack Torrance y protagonista de la novela el Resplandor, adaptada cinematográficamente por el excelso Stanley Kubrick.','https://www.syfy.com/sites/syfy/files/2019/06/danny_torrance_in_the_shining.jpg',238),(213,'Jack Torrance','Padre de Dannny Torrance, que enloquecerá y tratará de asesinar a su familia, influenciado por la maligna presencia que representa el hotel Overlook.','https://e00-marca.uecdn.es/assets/multimedia/imagenes/2019/10/07/15704577759307_1300x0.jpg',238),(216,'Thomas','Protagonista de la historia y crush de mi novia (pero no estoy celoso ehhhhh).','https://i.pinimg.com/originals/33/e3/82/33e3823dd9029bba9f071fd2436854e4.jpg',240),(217,'Newt','Aunque se llame así, no es nuevo en esto.','https://static.wikia.nocookie.net/the-maze-runner/images/5/51/Newt_Character_Still.png/revision/latest?cb=20131020233309&path-prefix=es',240),(218,'Teresa Agnes','La única mujer que hay; se puede decir que es una pitufina.','https://pbs.twimg.com/media/CfT4AdvUsAEJEyT.jpg',240),(219,'Juan Gómez-Jurado','El escritor de best sellers, famoso sobre todo por su nueva novela \"Reina roja\". Podéis disfrutar de su nuevo libro \"Todo arde\".','https://imagessl.casadellibro.com/img/autores/Gomez%20Jurado.jpg',241),(220,'Javier Cansado','Gran humorista que nunca se prepara los programas.','https://imagenes.elpais.com/resizer/EbND5XT4zaO5_7zuIBxugsAalWE=/1200x0/cloudfront-eu-central-1.images.arcpublishing.com/prisa/IQUJ6VT6JQCYZ2PPULUE2RNFFE.jpg',241),(221,'Arturo González-Campos','Director del programa que siempre te dará la enhorabuena por tu fracaso.','https://imagessl.casadellibro.com/img/autores/20098060.jpg',241),(222,'Rodrigo Cortés','El hombre que mejor habla en el mundo. Director de cine, con la popular películas \"Luces rojas\", y escritor, con su nueva obra \"Verbolario\".','https://static.abc.es/media/cultura/2021/05/30/rodrigo-cortes-khVG--1248x698@abc.jpg',241),(223,'Cthulhu','La criatura con el nombre más raro y que tanta controversia genera sobre su pronunciación.','https://static.wikia.nocookie.net/lovecraft/images/7/72/Lovecraft-cthulhu.jpg/revision/latest?cb=20140210145556&path-prefix=es',243),(224,'Dagón','Criatura temible que ronda en torno a mares de pesadillas.','https://static.wikia.nocookie.net/lovecraft/images/4/42/Dagon.jpg/revision/latest?cb=20140301124328&path-prefix=es',243),(225,'Nick Andros','El sordomudo con una vida intrincada y apasionante. Personalmente, mi personaje favorito.','https://static.wikia.nocookie.net/p__/images/f/fd/Tve10628-2376-19940508-0.jpg/revision/latest?cb=20160211060500&path-prefix=protagonist',244),(226,'Randall Flagg','Acaso, uno de los villanos más temibles y oscuros de la literatura de terror.','https://static.wixstatic.com/media/38031d_55283681fe164fca9af4bf6f46a55cff~mv2.jpg/v1/fit/w_598%2Ch_736%2Cal_c%2Cq_80/file.jpg',244),(227,'Frannie Goldsmith','La más mona y fuerte luchadora.','https://static.wikia.nocookie.net/stephenking/images/b/b7/Fran-2020-thestand.png/revision/latest?cb=20200703015155',244),(228,'Homer Simpson','Protagonista de la historia.','https://static.wikia.nocookie.net/lossimpson/images/b/bd/Homer_Simpson.png/revision/latest?cb=20100522180809&path-prefix=es',245),(229,'Bart Simpson','Hijo de Homer Simpson. El más travieso de todos los niños habidos y por conocer.','https://static.wikia.nocookie.net/lossimpson/images/6/65/Bart_Simpson.png/revision/latest?cb=20100530014756&path-prefix=es',245),(230,'Ayudante de Santa','La mascota de la familia Simpson.','https://static.wikia.nocookie.net/lossimpson/images/4/41/Ayudante_de_Santa.png/revision/latest?cb=20170610191956&path-prefix=es',245),(231,'Spiderman','El amigo y vecino Spiderman.','https://i.blogs.es/942f78/spider-man-3-tom-holland_8m76/1366_2000.jpeg',247),(232,'Iron man','El hombre de acero, el inteligente e intrépido Tony Stark.','https://static.wikia.nocookie.net/disney/images/9/96/Iron-Man-AOU-Render.png/revision/latest?cb=20180410032118&path-prefix=es',247),(233,'Hulk','El más bestia de todos cuando se descontrola.','https://static.wikia.nocookie.net/personajes-de-ficcion-database/images/0/0a/Hulk_UCM.png/revision/latest?cb=20200915215606&path-prefix=es',247),(234,'Jason Voorhees','Ten cuidado con el día que eliges para enfrentarte a él: si decides hacerlo un viernes trece, puedes dar por hecho que te destrozará.','https://imagenes.20minutos.es/files/image_990_v3/uploads/imagenes/2022/11/01/jason-voorhees.jpeg',248),(235,'Michael Myers','El despiadado enmascarado que aterroriza a toda una generación y uno de los disfraces más frecuentados en Halloween.','https://upload.wikimedia.org/wikipedia/en/e/e9/MichaelMyers2018.jpg',248),(236,'Scream','Sin duda, vais a gritar de encontraros con él.','https://www.clarin.com/img/2022/01/12/ghostface-ataca-de-nuevo-por___PLgDxKqeH_2000x1500__1.jpg',248),(237,'Chucky','El muñeco diabólico más perturbador y tenebroso del cine; haría una buena pareja con Anabelle.','https://static.wikia.nocookie.net/chucky-el-muneco-diabolico/images/d/da/Teaser-trailer-chucky.jpg/revision/latest?cb=20211021064232&path-prefix=es',248),(253,'Batman','Si aparece la batseñal, que todos los villanos se escondan, porque aparecerá el caballero oscuro a combatir el mal.','https://img2.rtve.es/i/?w=1600&i=1634549481357.jpg',254),(254,'Joker','Quizá, el villano más querido por los fans. Este loco psicótico, es capaz de enloquecer y provocar el caos pasa.','https://as01.epimg.net/meristation/imagenes/2022/09/08/noticias/1662628621_660399_1662628654_noticia_normal.jpg',254),(255,'Superman','Con una fuerza y poder descomunal, acaso lo único que lo puede detener es la kryptonita.','https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/03/16595421832009.jpg',254);
/*!40000 ALTER TABLE `cards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deck_keywords`
--

DROP TABLE IF EXISTS `deck_keywords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deck_keywords` (
  `idKeyword` int NOT NULL,
  `idDeck` int NOT NULL,
  PRIMARY KEY (`idKeyword`,`idDeck`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deck_keywords`
--

LOCK TABLES `deck_keywords` WRITE;
/*!40000 ALTER TABLE `deck_keywords` DISABLE KEYS */;
INSERT INTO `deck_keywords` VALUES (74,237),(74,245),(74,247),(75,237),(75,247),(75,253),(76,238),(76,243),(76,244),(76,248),(77,238),(78,238),(79,238),(79,240),(79,243),(79,244),(79,247),(80,239),(80,249),(80,255),(80,256),(81,240),(81,247),(81,248),(81,252),(82,240),(83,241),(84,241),(84,254),(85,241),(86,241),(86,245),(87,245),(88,246),(88,247),(89,247),(90,247),(91,247),(91,254),(92,247),(92,251),(93,247),(94,247),(95,247),(96,247),(97,253);
/*!40000 ALTER TABLE `deck_keywords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `decks`
--

DROP TABLE IF EXISTS `decks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `decks` (
  `idDeck` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `nCards` int NOT NULL,
  `nCategories` int NOT NULL,
  `image` varchar(4000) DEFAULT NULL,
  `idUser` int NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `published` tinyint(1) NOT NULL,
  `borde` varchar(20) NOT NULL,
  `fondo` varchar(20) NOT NULL,
  `panel` varchar(20) NOT NULL,
  `fuente` varchar(20) NOT NULL,
  PRIMARY KEY (`idDeck`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=257 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `decks`
--

LOCK TABLES `decks` WRITE;
/*!40000 ALTER TABLE `decks` DISABLE KEYS */;
INSERT INTO `decks` VALUES (237,'Pokemon',4,6,'https://media.vandal.net/i/1200x630/10-2021/2021105724573_1.jpg',23,'Este mazo reúne algunos de los más laudables e importantes pokemons que componen la Pokédex. Jugando con este magnífico, podrá revivir recuerdos nostálgicos de la infancia. Como creador, espero que les guste el mazo que he confeccionado.',1,'#000000','#0ed600','#ddaf08','#000000'),(238,'Stephen King',3,6,'https://as01.epimg.net/meristation/imagenes/2022/05/13/noticias/1652443667_054489_1652447928_noticia_normal.jpg',24,'Este mazo recoge gran parte de los personajes más relevantes del universo del autor de terror contemporáneo por esencia. Podrá disfrutar de estas carta, de las que podrá aprender sobre sus personajes; revivir los recuerdos de cuando leyeron algunas de las novelas o relatos donde aparecen. Grandes personajes que han cautivado a millones de lectores constantes, enganchados a todo lo que Stephen King escribe. Espero que disfruten del mazo, que he diseñado con tanto cariño. Soy muy forofo del autor.',1,'#000000','#0ed600','#ddaf08','#000000'),(240,'Corredor del Laberinto',3,6,'https://uploads-ssl.webflow.com/60a78e52a3ba3a525a3b96cf/60ace264d0c4eab542a5da62_el-corredor-del-laberinto-curiosidades.jpeg',23,'Mazo que tiene las cartas que tiene algunos de los personajes más importantes de la película.',1,'#000000','#0ed600','#ddaf08','#000000'),(241,'Todopoderosos',4,6,'http://static-1.ivoox.com/canales/2/1/6/1/5661525711612_XXL.jpg',23,'Mazo con las cartas representativas de los todopoderosos, que presentan este magnífico podcast.',1,'#000000','#0ed600','#ddaf08','#000000'),(243,'H.P Lovecraft',2,6,'https://cdn.normacomics.com/media/catalog/product/cache/1/thumbnail/9df78eab33525d08d6e5fb8d27136e95/l/o/lovecraft_vida_ilustrada.jpg',23,'Mazo que recoge muchas de las más populares criaturas lovecraftnianas.',1,'#000000','#0ed600','#ddaf08','#000000'),(244,'Apocalipsis',3,6,'https://m.media-amazon.com/images/I/81xxoe+bmXL.jpg',26,'Este mazo reúne gran parte de los personajes que compone las famosa novela de Stephen King. ',1,'#050505','#e10909','#999494','#000000'),(245,'Simpsons',3,6,'https://sire-media-foxes.fichub.com/generic/serie-main/473.1024x576.jpg',26,'Mazo que recoge algunos de los personajes más populares de la serie de TV de dibujos animados.',1,'#fbe704','#efe6e6','#0294c5','#000000'),(247,'Marvel',3,6,'https://upload.wikimedia.org/wikipedia/commons/thumb/b/b9/Marvel_Logo.svg/1200px-Marvel_Logo.svg.png',23,'Mazo que recoge varios de los personajes del universo de Marvel.',1,'#f5f5f5','#ed0707','#FFFFFF','#000000'),(248,'Slasher Movies',4,6,'https://media.vandalsports.com/i/640x360/12-2021/2021127192842_1.jpg',28,'En este mazo se representan muchos de los clásicos personajes que protagonizan las películas pertenecientes a este género. Esta mazo va dedicado a todos los forofos incondicionales del terror. Espero de todo corazón que os guste y paséis momentos divertidos utilizando estas cartas, que he diseñado con esmero y cariño.',1,'#c50d0d','#474343','#1c22d9','#000000'),(254,'DC Comics',3,6,'https://i0.wp.com/imgs.hipertextual.com/wp-content/uploads/2015/02/dc-comics-universe.jpg?fit=1200%2C750&quality=60&strip=all&ssl=1',37,'Mazo que recoge muchos de los personajes más destacados que conforman el universo de DC Comics. Espero que les guste este mazo que he diseñado con mucho cariño.',1,'#001dfa','#d8caca','#f9d801','#000000');
/*!40000 ALTER TABLE `decks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `keywords`
--

DROP TABLE IF EXISTS `keywords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `keywords` (
  `idKeyword` int NOT NULL AUTO_INCREMENT,
  `word` varchar(45) NOT NULL,
  PRIMARY KEY (`idKeyword`),
  UNIQUE KEY `word_UNIQUE` (`word`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `keywords`
--

LOCK TABLES `keywords` WRITE;
/*!40000 ALTER TABLE `keywords` DISABLE KEYS */;
INSERT INTO `keywords` VALUES (93,'Acción'),(95,'Aventura'),(81,'Cine'),(91,'Comics'),(84,'Cultura'),(74,'Dibujos animados'),(96,'Entretenimiento'),(75,'Fantasía'),(89,'Ficción'),(86,'Humor'),(79,'Literatura'),(94,'Magia'),(92,'Novela gráfica'),(78,'Novelas'),(97,'Novelas gráficas'),(82,'Películas'),(83,'Podcast'),(85,'Radio'),(87,'Serie TV'),(88,'Superhéroes'),(76,'Terror'),(77,'Thriller'),(90,'Villanos');
/*!40000 ALTER TABLE `keywords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `idUser` int NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `password` varchar(14) NOT NULL,
  `name` varchar(25) NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (23,'Pablosky','pablosky','Pablosky'),(24,'Dario','dario1234567','Dario'),(25,'Tester','testertester','Tester'),(26,'Nick','nicknick','Nick'),(27,'Gordi','gordigordi','Gordi'),(28,'Paulo','paulopaulo','Paulo'),(29,'Kilig','kiligkilig','Kilig'),(30,'Dexter','dexterdexter','Dexter'),(31,'Ben','benbenben','Ben'),(32,'Peibol','peibolpeibol','Peibol'),(37,'Pareto','paretopareto','Pareto');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-25  8:45:32
