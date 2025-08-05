package ninja;

import jack.Jack;
import map.mapItems.Floor;
import model.CollisionHandler;
import model.Item;
import model.Sprite;
import monster.Monster;

import java.awt.*;


public class NinjaCollisionHandler implements CollisionHandler {
    @Override
    public void handle(Point originalLocation, Sprite from, Sprite to) {
        if (from instanceof Ninja && (to instanceof Ninja || to instanceof Monster)){
            Rectangle body = from.getBody();
            int offsetLeft = to.getBody().x - body.x;
            int offsetRight = body.x + body.width - to.getBody().x;
            if (offsetLeft < 0 ) {
                to.setLocation(new Point(to.getX() - (to.getBody().width + offsetLeft), to.getY()));
            } else {
                to.setLocation(new Point(to.getX() + offsetRight, to.getY()));
            }
        }else if(from instanceof Monster && to instanceof Ninja){
            Rectangle body = from.getBody();
            int offsetLeft = to.getBody().x - body.x;
            int offsetRight = body.x + body.width - to.getBody().x;
            if (offsetLeft < 0) {
                from.setLocation(new Point(from.getX() + (to.getBody().x + to.getBody().width - from.getBody().x), from.getY()));
            } else {
                from.setLocation(new Point(from.getX() - (from.getBody().width - offsetLeft), from.getY()));
            }
        }else if(!(to instanceof Jack || from instanceof Jack)){
            Rectangle body = from.getBody();
            int offsetLeft = to.getBody().x - body.x;
            int offsetRight = body.x + body.width - to.getBody().x;
            if (offsetLeft < 0 ) {
                to.setLocation(new Point(to.getX() - (to.getBody().width + offsetLeft), to.getY()));
            } else {
                to.setLocation(new Point(to.getX() + offsetRight, to.getY()));
            }
        }
    }

    @Override
    public void handle(Point originalLocation, Sprite from, Item to) {
        if(from instanceof Jack && to instanceof Floor){
            Rectangle body = from.getBody();
            int Bottom = (body.y + body.height);
            int Top = body.y;
            int Right = body.x + body.width;
            int Left = body.x;
            if(Right > to.getX()+to.getRange().width && Left < to.getX()+to.getRange().width){ //Item right side
                if(Top > to.getY()-10 && Bottom < to.getY() + to.getRange().height+10){
                    from.setLocation(new Point(to.getX()+to.getRange().width, from.getY()));
                }else if(Bottom > to.getY() + to.getRange().height){
                }else{
                    from.setLocation(new Point(from.getX(), to.getY() - from.getRange().height));
                }
            }else if(Right > to.getX() && Left < to.getX()){ //Item left side
                if(Top > to.getY()-10 && Bottom < to.getY() + to.getRange().height+10){
                    from.setLocation(new Point(to.getX()-from.getRange().width, from.getY()));
                }else if(Bottom > to.getY() + to.getRange().height){
                }else{
                    from.setLocation(new Point(from.getX(), to.getY() - from.getRange().height));
                }
            }else{
                if(Top < to.getY() + to.getRange().height && Bottom > to.getY() + to.getRange().height){
                }else{
                    from.setLocation(new Point(from.getX(), to.getY() - from.getRange().height));
                }
            }
        }else if(to instanceof Floor) {
            Rectangle body = from.getBody();
            int Bottom = (body.y + body.height);
            int Top = body.y;
            int Right = body.x + body.width;
            int Left = body.x;
            if(Right > to.getX()+to.getRange().width && Left < to.getX()+to.getRange().width){ //Item right side
                if((Top < to.getY()+5 && Bottom > to.getY() + to.getRange().height-5) ||
                    (Top > to.getY()-10 && Bottom < to.getY() + to.getRange().height+10)){
                    from.setLocation(new Point(to.getX()+to.getRange().width, from.getY()));
                }else if(Bottom > to.getY() + to.getRange().height){
                    from.setLocation(new Point(from.getX(), to.getY() + to.getRange().height));
                }else{
                    from.setLocation(new Point(from.getX(), to.getY() - from.getRange().height));
                }
            }else if(Right > to.getX() && Left < to.getX()){ //Item left side
                if((Top < to.getY()+5 && Bottom > to.getY() + to.getRange().height-5) ||
                   (Top > to.getY()-10 && Bottom < to.getY() + to.getRange().height+10)){
                    from.setLocation(new Point(to.getX()-from.getRange().width, from.getY()));
                }else if(Bottom > to.getY() + to.getRange().height){
                    from.setLocation(new Point(from.getX(), to.getY() + to.getRange().height));
                }else{
                    from.setLocation(new Point(from.getX(), to.getY() - from.getRange().height));
                }
            }else{
                if(Top < to.getY() + to.getRange().height && Bottom > to.getY() + to.getRange().height){
                    from.setLocation(new Point(from.getX(), to.getY() + to.getRange().height));
                }else{
                    from.setLocation(new Point(from.getX(), to.getY() - from.getRange().height));
                }
            }
        }
    }
}
