package interviewquestions.google;

import java.util.*;

/**
 * You need to develop the game Snake. What data structures will you use? Code
 * your solution.
 * 
 * @author zhangxiaokang
 */
public class SnakePrototype {
    class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Position(Position position) {
            this(position.x, position.y);
        }
    }

    class Cell {
        private Position cellPosition;
        public String content;

        public Cell(String content, int x, int y) {
            this.content = content;
            this.cellPosition = new Position(x, y);
        }

        public Position getPosition() {
            return new Position(cellPosition);
        }
    }

    class Eat extends Cell {
        public Eat(int x, int y) {
            super("E", x, y);
        }
    }

    class SnakeBody extends Cell {
        public SnakeBody(int x, int y) {
            super("S", x, y);
        }
    }

    class Snake {
        Queue<SnakeBody> snake;
        SnakeBody head;
        boolean isAlive;

        public Snake(int x, int y) {
            snake = new LinkedList<SnakeBody>();
            head = new SnakeBody(x, y);
            snake.offer(head);
            isAlive = true;
        }

        public void moveUp() {

        }

        public void moveDown() {

        }

        public void moveLeft() {

        }

        public void moveRight() {

        }

        public boolean isCollide() {
            return false;
        }

        public SnakeBody getHead() {
            return head;
        }
    }

    class Board {
        Cell[][] board;

        public Board(int N) {
            board = new Cell[N][N];
        }

        public void draw(Cell cell) {

        }

        public void clear(Cell cell) {

        }

        public boolean hitWall(Cell cell) {
            return true;
        }
    }

    public void play() {

    }

    public static void main(String[] args) {

    }
}
