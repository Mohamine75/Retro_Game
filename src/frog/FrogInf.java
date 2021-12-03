package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import util.Case;
import util.Direction;

public class FrogInf implements  IFrog {
    private Case position;
    private Direction direction;
    private final Game game;
    private Integer score;
    private Integer scoreMax;

    public FrogInf(Game game) {
        this.position = new Case(game.width / 2, 1);
        this.game = game;
        this.score = 0;
        this.scoreMax = 0;
        direction = Direction.up;
    }

    public Case getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    public Integer getScoreMax() {
        return scoreMax;
    }

    public Integer getScore() {
        return score;
    }

    public void setPosition(Case position) {
        this.position = position;
    }

    public void move(Direction key) {
        switch (key) {
            case right:
                if(!game.getEnvironment().getLane(position.ord).forbidden(new Case(position.absc + 1, position.ord))) {
                    if (position.absc + 1 <= game.width) {
                        this.position = new Case(position.absc + 1, position.ord);
                    }
                }
                direction = Direction.right;
                break;
            case left:
                if(!game.getEnvironment().getLane(position.ord).forbidden(new Case(position.absc-1, position.ord))) {
                    if (position.absc - 1 >= 0) {
                        this.position = new Case(position.absc - 1, position.ord);
                    }
                }
                direction = Direction.left;
                break;
            case up:
                if(!game.getEnvironment().getLane(position.ord+1).forbidden(new Case(position.absc, position.ord+1))) {
                    this.direction = Direction.up;
                    this.score++;
                    if (score > scoreMax) {
                        scoreMax = score;
                    }
                    game.getEnvironment().decalageDown();
                }
                direction = Direction.up;
                break;
            case down:
                if(!game.getEnvironment().getLane(position.ord-1).forbidden(new Case(position.absc, position.ord - 1))) {
                    if (score >= 1) {
                        game.getEnvironment().decalageUp();
                    }
                    if (score > 0) {
                        this.score--;
                    }
                }
                direction = Direction.down;
                break;
        }
    }

    public void setScore(int bonus){
        this.score += bonus;
    }

}

