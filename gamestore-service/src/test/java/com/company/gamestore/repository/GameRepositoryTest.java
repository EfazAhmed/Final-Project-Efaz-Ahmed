package com.company.gamestore.repository;

import com.company.gamestore.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GameRepositoryTest {
    @Autowired
    GameRepository gameRepository;

    Game game1;

    Game game2;

    @BeforeEach
    public void setUp() throws Exception {
        gameRepository.deleteAll();

        game1 = new Game();
        game1.setTitle("Guilty Gear Xrd REV2");
        game1.setEsrbRating("T");
        game1.setDescription("Astonishing 3D cell animations and overwhelming content volume, the next-generation fighting game GUILTY GEAR Xrd REV 2 is now out on Steam!");
        game1.setPrice(BigDecimal.valueOf(29.99));
        game1.setStudio("Arc System Works");
        game1.setQuantity(500);
        game1 = gameRepository.save(game1);

        game2 = new Game();
        game2.setTitle("Mario Kart 8");
        game2.setEsrbRating("E");
        game2.setDescription("This is a kart-racing game in which players compete with characters from the Mario universe.");
        game2.setPrice(BigDecimal.valueOf(59.99));
        game2.setStudio("Nintendo");
        game2.setQuantity(300);
        game2 = gameRepository.save(game2);
    }

    @Test
    public void addGame() {
        Game game = new Game();
        game.setTitle("Super Mario Bros. Wonder");
        game.setEsrbRating("E");
        game.setDescription("Mario and friends run across a colorful landscape that is being magically transformed as they go.");
        game.setPrice(BigDecimal.valueOf(59.99));
        game.setStudio("Nintendo");
        game.setQuantity(100);

        assertEquals(2, gameRepository.findAll().size());
        game = gameRepository.save(game);
        assertEquals(3, gameRepository.findAll().size());
    }

    @Test
    public void getGameById() {
        Optional<Game> newGame = gameRepository.findById(game1.getId());
        assertEquals(newGame.get(), game1);
    }

    @Test
    public void getAllGames() {
        assertEquals(2, gameRepository.findAll().size());
    }

    @Test
    public void updateGame() {
        game1.setQuantity(469);
        gameRepository.save(game1);

        Optional<Game> newGame = gameRepository.findById(game1.getId());
        assertEquals(newGame.get(), game1);
    }

    @Test
    public void deleteGameById() {
        gameRepository.deleteById(game1.getId());
        Optional<Game> newGame = gameRepository.findById(game1.getId());
        assertFalse(newGame.isPresent());
    }

    @Test
    public void findGamesByStudio() {
        List<Game> games = gameRepository.findByStudio(game1.getStudio());
        assertEquals(1,games.size());
    }

    @Test
    public void findGamesByEsrbRating() {
        List<Game> games = gameRepository.findByEsrbRating(game1.getEsrbRating());
        assertEquals(1,games.size());
    }

    @Test
    public void findGamesByTitle() {
        List<Game> games = gameRepository.findByTitle(game1.getTitle());
        assertEquals(1,games.size());
    }

}
