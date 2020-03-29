package pl.sda.javagda28.tourmanagingcrud;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.tomcat.jni.Local;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sda.javagda28.tourmanagingcrud.entity.*;
import pl.sda.javagda28.tourmanagingcrud.repository.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TestDataRunner implements CommandLineRunner {

    private final VenueRepository venueRepository;
    private final BandRepository bandRepository;
    private final EventRepository eventRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void run(final String... args) throws Exception {
        Venue stadion = venueRepository.save(new Venue(null, "Stadion Narodowy", "Warszawka", "Stadion Narodowy w Warszawie, od lipca 2015 pod nazwą PGE Narodowy – wielofunkcyjny stadion sportowy znajdujący się przy al. Księcia Józefa Poniatowskiego 1 w Warszawie. Został wybudowany w latach 2008–2011 w miejscu Stadionu Dziesięciolecia przed piłkarskimi Mistrzostwami Europy 2012." ,null));
        venueRepository.save(new Venue(null, "Stadion Energa", "Gdańsk", "Stadion Energa Gdańsk – stadion piłkarski w Gdańsku, znajdujący się przy ulicy Pokoleń Lechii Gdańsk 1, w dzielnicy Letnica. Stanowi własność miasta Gdańska, a jego głównym użytkownikiem jest klub piłkarski Lechia Gdańsk.", null));
        venueRepository.save(new Venue(null, "Atlas Arena", "Lódź", "Atlas Arena – hala sportowo-widowiskowa w Łodzi. Trybuny mogą pomieścić 10.049 widzów, a na płycie można ustawić dodatkowo 3 tys. miejsc; do dyspozycji gości dostępnych jest 1500 miejsc postojowych, 4 ekrany multimedialne, i 11 loży VIP-owskich.", null));

        Band metallica = bandRepository.save(new Band(null, "Metallica", "thrash metal", 4L,"Amerykański zespół heavymetalowy i thrashmetalowy założony w Los Angeles w 1981 roku przez Jamesa Hetfielda i Larsa Ulricha. Uważany za jeden z najważniejszych, najbardziej wpływowych zespołów metalowych lat 80. XX wieku, w latach 90. ubiegłego stulecia za najbardziej dochodowy zespół metalowy, a także za jeden z najpopularniejszych i najwybitniejszych zespołów heavymetalowych w dziejach. Według danych szacunkowych, nakład ze sprzedaży wszystkich wydawnictw muzycznych Metalliki na całym świecie wyniósł ponad 150–200 milionów egzemplarzy, zgodnie z oficjalnym raportem International Federation of the Phonographic Industry z 2006 roku – międzynarodowej i jedynej na świecie organizacji przemysłu muzycznego, która zlicza sprzedaż formatów wydawniczych z całego globu. Tym samym, Międzynarodowa Federacja Przemysłu Fonograficznego uznała zespół formacją heavymetalową z największą liczbą sprzedanych wydawnictw muzycznych w historii, zgodnie ze wszystkimi oficjalnymi certyfikatami sprzedaży. Są również dziewiątymi artystami (a trzynastymi w klasyfikacji generalnej obok Led Zeppelin i Julio Iglesiasa), którzy sprzedali najwięcej płyt w historii muzyki. W samych Stanach Zjednoczonych, według Recording Inustry Association of America, zrzeszenia wydawców fonografii przyznającego oficjalne certyfikaty za sprzedaż płyt i singli w USA nakład ze sprzedaży płyt zespołu przekroczył 99 milionów 500 tysięcy kopii. Od 1991 roku w Polsce sprzedaż albumów Metalliki sięgnęła liczby 866 tysięcy egzemplarzy według Związku Producentów Audio-Video, przyznającego oficjalne certyfikaty za sprzedaż wydawnictw muzycznych. Czyni ich to drugimi zagranicznymi artystami z największą liczbą sprzedanych płyt w Polsce. Zespół jest jedną z najbardziej dochodowych grup muzycznych ostatnich trzech dekad pod względem sprzedaży koncertów. Do 2014 roku Metallica zarobiła na trasach koncertowych 433 miliony dolarów. W 2019 roku serwis Pollstar sklasyfikował zespół jako najlepiej zarabiający artysta koncertujący.","wsrvmNtWU4E" ,null));
        bandRepository.save(new Band(null, "Anthrax", "thrash metal", 5L,"Anthrax – amerykański zespół założony w Nowym Jorku w 1981 roku. Zaliczany obok zespołów Metallica, Slayer i Megadeth do wielkiej czwórki thrash metalu.", "be7iNHw8QoQ" ,null));
        Band slayer = bandRepository.save(new Band(null, "Slayer", "thrash metal", 4L, "Slayer – amerykańska grupa muzyczna wykonująca thrash metal, powstała w 1981 roku w Huntington Park (przedmieścia Los Angeles), rozwiązana w 2019 roku. Zespół powstał z inicjatywy gitarzysty Kerry’ego Kinga, który do współpracy zaprosił gitarzystę Jeffa Hannemana, pochodzącego z Kuby perkusistę Dave’a Lombardo oraz basistę i wokalistę Chilijczyka - Toma Arayę. Już w początkowym okresie działalności muzycy rozwinęli własny styl oparty na intensywnych partiach gitar z partiami solowymi wykonywanymi przy użyciu tremola oraz charakterystycznym śpiewie wokalisty zespołu. Cztery z dziesięciu albumów zespołu zyskały w Stanach Zjednoczonych status złotej płyty. Wieloletnim współpracownikiem grupy był producent muzyczny Rick Rubin współpracujący również z takimi zespołami jak Beastie Boys, LL Cool J, Danzig, Metallica czy System of a Down. Slayer, wraz z grupami Metallica, Anthrax i Megadeth zaliczany jest do tzw. „Wielkiej Czwórki” thrash metalu, wywierając wpływ i inspiracje dla przyszłych wykonawców nurtu, takich jak Chimaira, Mnemic czy Vader. W 2018 roku zespół ogłosił zakończenie trzydziestosiedmioletniej kariery po pożegnalnej trasie, mającej odbyć się w tymże roku.", "z8ZqFlw6hYg" ,null));
        bandRepository.save(new Band(null, "Megadeth", "thrash metal", 4L, "Megadeth – amerykańska grupa muzyczna założona w kwietniu 1983 roku w Los Angeles w Kalifornii. Powstała z inicjatywy Dave’a Mustaine’a i Dave’a Ellefsona. Nazwa zespołu to fonetyczne brzmienie angielskiego (megadeath) określenia hipotetycznej jednostki miar, oznaczającej liczbę miliona osób, które zginęłyby w wyniku eksplozji nuklearnej[2]. Formacja reprezentuje takie style muzyczne jak thrash metal, speed metal, heavy metal i metal progresywny. Nakład ze sprzedaży wszystkich płyt grupy wynosi ponad 38 milionów egzemplarzy na całym świecie. Zespół zdobył sześć platynowych płyt i siedem nominacji do nagród Grammy. Należy do Wielkiej Czwórki Thrash Metalu, razem z zespołami Metallica, Slayer i Anthrax. Otrzymali nagrodę Grammy w kategorii Best Metal Performance w 2017 roku. Muzyka Megadeth charakteryzuje się dynamicznymi kompozycjami z bardzo szybko i agresywnie granymi riffami gitar. Łatwo rozpoznawalny jest również śpiew Dave’a Mustaine’a.", "K5jvUXij7nU" ,null));

        eventRepository.save(new Event(null,"Sonisphere Festival", LocalDateTime.now(),"Sonisphere Festival – festiwal rockowy złożony z serii koncertów odbywających się w różnych miastach europejskich. Pomysłodawcą festiwalu był Stuart Galbraith. Pierwsza edycja miała miejsce w 2009 roku, festiwal składał się z pięciu koncertów jednodniowych (w Nijmegen (Holandia), Hockenheimring (Niemcy), Barcelonie (Hiszpania), Hultsfred (Szwecja) i Pori (Finlandia)) oraz dwudniowego w Knebworth (Anglia). Na wszystkich sześciu koncertach wystąpiły zespoły Metallica, Mastodon oraz Lamb of God." , Arrays.asList(metallica), stadion));
        eventRepository.save(new Event(null,"Mystic Festival", LocalDateTime.now(), "Mystic Festival – festiwal odbywający się co roku w Krakowie, poświęcony wszystkim odcieniom muzyki metalowej. Organizatorem imprezy jest Mystic Coalition, sojusz firm, w skład którego wchodzą: Knock Out Productions, Mystic Production i B90. Od 2020 roku Mystic Festival odbywa się na terenie krakowskiego Muzeum Lotnictwa.", Arrays.asList(metallica, slayer), stadion));

        final Role roleAdmin = new Role("ROLE_ADMIN");
        roleRepository.save(roleAdmin);

        final Role roleBand = new Role("ROLE_BAND");
        roleRepository.save(roleBand);

        final Role roleOrganiser = new Role("ROLE_ORGANISER");
        roleRepository.save(roleOrganiser);

        final User user = new User(null,"admin", "admin@admins.com",
                passwordEncoder.encode("admin"), List.of(),
                List.of(roleAdmin));
        userRepository.save(user);

        final User bandUser = new User(null,"band", "band@band.com",
                passwordEncoder.encode("band"),List.of(), List.of(roleBand));
        userRepository.save(bandUser);

        final User organiserUser = new User(null, "organiser", "organiser@organiser.com",
                passwordEncoder.encode("organiser"), List.of(), List.of(roleOrganiser));
        userRepository.save(organiserUser);
    }
}
