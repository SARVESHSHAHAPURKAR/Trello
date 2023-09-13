package Services;

import Interfaces.ManagerInterface;
import Models.Card;
import Models.CustomList;
import Models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ListManager implements ManagerInterface {

    List<CustomList> customLists;

    CardManager cardManager;

    public ListManager(List<CustomList> customLists, CardManager cardManager) {
        this.customLists = customLists;
        this.cardManager = cardManager;
    }

    public List<CustomList> getCustomLists() {
        return customLists;
    }

    public void setCustomLists(List<CustomList> customLists) {
        this.customLists = customLists;
    }

    public CardManager getCardManager() {
        return cardManager;
    }

    public void setCardManager(CardManager cardManager) {
        this.cardManager = cardManager;
    }

    @Override
    public void show(UUID id) {

        for(CustomList list: customLists){

            if(list.getId().equals(id)){
                System.out.println("List id is "+list.getId()+" name is "+list.getName());

                if(list.getCards()!=null) {
                    for (Card card : list.getCards()) {
                        cardManager.show(card.getId());
                    }
                }
            }
        }
    }


    public void createCard(UUID listId, Card card) {

        cardManager.getCards().add(card);

        for(CustomList customList: customLists){
            if(customList.getId().equals(listId)){

                if(customList.getCards()==null){
                    customList.setCards(new ArrayList<Card>());
                }
                customList.getCards().add(card);
            }
        }
    }

    public void updateCardName(UUID id, String name) {
        cardManager.updateName(id,name);
    }

    public void updateCardDesc(UUID id, String desc) {
        cardManager.updateDesc(id,desc);
    }


    public void updateCardAssignee(UUID id, User assignee) {
        cardManager.updateAssignee(id,assignee);
    }

    public void unassignFromCard(UUID id) {
        cardManager.unassignFromCard(id);
    }

    public void moveCard(UUID cardId, UUID listId) {

        Card targetCard = null;

        for(CustomList customList : customLists){

            for(Card card: customList.getCards()){
                if(card.getId().equals(cardId)){
                    targetCard = card;
                    customList.getCards().remove(card);
                    break;
                }
            }

        }

        for(CustomList customList : customLists){

            if(customList.getId().equals(listId)){
                customList.getCards().add(targetCard);
            }
        }


    }
}
