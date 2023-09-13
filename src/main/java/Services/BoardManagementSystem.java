package Services;

import Interfaces.ManagerInterface;
import Models.Board;
import Models.Card;
import Models.CustomList;
import Models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BoardManagementSystem implements ManagerInterface {

    List<Board> boards;

    ListManager listManager;

    public BoardManagementSystem(List<Board> boards, ListManager listManager) {
        this.boards = boards;
        this.listManager = listManager;
    }

    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }

    public ListManager getListManager() {
        return listManager;
    }

    public void setListManager(ListManager listManager) {
        this.listManager = listManager;
    }

    public void showAll(){

        for(Board board: boards){
            show(board.getId());
        }
    }

    @Override
    public void show(UUID id) {

        System.out.println("Showing board details");
        Board board = null;
        for(Board board1: boards){
            if(board1.getId().equals(id)){
                board = board1;
            }
        }
        System.out.println("Board id is "+ board.getId()+" name is "+board.getName()+
                " privacy is "+board.getPrivacy()+ " url is "+board.getUrl());

        System.out.println("The members are ");
        printMembers(board);

        System.out.println("The lists are ");
        printLists(board);

    }

    private void printLists(Board board) {

        for(CustomList customList : board.getCustomLists()){
            listManager.show(customList.getId());
        }
    }

    private void printMembers(Board board) {

        for(User member : board.getMembers()){
            System.out.println("Member name is "+member.getName()+" email id is "+member.getEmail()
            +" id is "+member.getUserId());
        }
    }

    public UUID createBoard(String name, String privacy, String url, List<User> members, List<CustomList> lists ){

        Board board = new Board();

        board.setId(UUID.randomUUID());
        board.setName(name);

        if(privacy!=null){
            board.setPrivacy(privacy);
        }
        else{
            board.setPrivacy("PUBLIC");
        }

        board.setUrl(url);

        if(members==null){
            List<User> members1 = new ArrayList<>();
            board.setMembers(members1);
        }
        else{
            board.setMembers(members);
        }

        if(lists==null){
            List<CustomList> customLists = new ArrayList<>();
            board.setCustomLists(customLists);
        }
        else{
            board.setCustomLists(lists);
        }

        System.out.println("Board created with id "+board.getId());

        boards.add(board);
        return board.getId();
    }

    public void updateBoardName(UUID id,String name ){

        //Board board= null;

        for(Board board: boards){
            if(board.getId().equals(id)){
                board.setName(name);
                System.out.println("Board name for "+board.getId()+" updated to "+board.getName());
                return;
            }
        }

        System.out.println("Board not found");
    }

    public void updateBoardPrivcy(UUID id,String privacy ){

        //Board board= null;

        for(Board board: boards){
            if(board.getId().equals(id)){
                board.setPrivacy(privacy);
                System.out.println("Board privacy for "+board.getId()+" updated to "+board.getPrivacy());
                return;
            }
        }

        System.out.println("Board not found");
    }

    public void updateBoardUrl(UUID id,String url ){

        //Board board= null;

        for(Board board: boards){
            if(board.getId().equals(id)){
                board.setUrl(url);
                System.out.println("Board url for "+board.getId()+" updated to "+board.getUrl());
                return;
            }
        }

        System.out.println("Board not found");
    }

    public void addMember(UUID id, User user){

        for(Board board: boards){
            if(board.getId().equals(id)){
                board.getMembers().add(user);
            }
        }
    }

    public void deleteBoard(UUID id){

        for(Board board: boards){
            if(board.getId().equals(id)){
                boards.remove(board);
            }
        }

    }

    public void removeMember(UUID id, User user){

        for(Board board: boards){
            if(board.getId().equals(id)){
                board.getMembers().remove(user);
            }
        }
    }

    public UUID createList(UUID boardId, String name){

        CustomList customList = new CustomList();
        customList.setId(UUID.randomUUID());

        customList.setName(name);

        System.out.println();

        listManager.getCustomLists().add(customList);

        for(Board board: boards){
            if(board.getId().equals(boardId)){
                board.getCustomLists().add(customList);
            }
        }

        return customList.getId();

    }

    public void updateList(UUID listId, String name){

        for(CustomList customList : listManager.getCustomLists()){
            if(customList.getId().equals(listId)){
                customList.setName(name);
            }
        }
    }

    public UUID createCard(UUID listId, String name ){

        Card card = new Card();
        card.setId(UUID.randomUUID());
        card.setName(name);

        System.out.println("Card created with id "+card.getId());

        listManager.createCard(listId, card);

        return card.getId();
    }

    public void updateCardName(UUID id, String name){

        listManager.updateCardName(id,name);
    }

    public void updateCardDesc(UUID id, String desc){

        listManager.updateCardDesc(id,desc);
    }

    public void updateCardAssignee(UUID id, User assignee){

        listManager.updateCardAssignee(id,assignee);
    }

    public void unassignFromCard(UUID id){
        listManager.unassignFromCard(id);
    }

    public void moveCard(UUID cardId, UUID listId){
        listManager.moveCard(cardId, listId);
    }

}
