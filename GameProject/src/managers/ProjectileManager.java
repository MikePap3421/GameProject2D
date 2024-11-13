package managers;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.Enemy;
import helpz.LoadSave;
import objects.Projectile;
import objects.Tower;
import scenes.PLAYING;
import scenes.PLAYING;
import static helpz.constants.Towers.*;
import static helpz.constants.Projectiles.*;

public class ProjectileManager {

    private PLAYING playing;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private BufferedImage[] proj_imgs;
    private int proj_id = 0;

    public ProjectileManager(PLAYING playing) {
        this.playing = playing;
        importImgs();
    }

    private void importImgs() {
        BufferedImage atlas = LoadSave.getspriteatlas2();
        proj_imgs = new BufferedImage[3];
        proj_imgs[1] = atlas.getSubimage( 9* 64, 4*64, 64, 64);
        proj_imgs[0] = atlas.getSubimage( 8* 64, 5*64, 64, 64);
        proj_imgs[2] = atlas.getSubimage( 9* 64, 5*64, 64, 64);
    }

    public void newProjectile(Tower t, Enemy e) {
        int type = getProjType(t);

        int xDist = ((int) (t.getX() - e.getX())*2);
        int yDist = ((int) (t.getY() - e.getY())*2);
        int totDist = Math.abs(xDist) + Math.abs(yDist);

        float xPer = (float) Math.abs(xDist) / totDist;

        float xSpeed = xPer * helpz.constants.Projectiles.GetSpeed(type);
        float ySpeed = helpz.constants.Projectiles.GetSpeed(type) - xSpeed;

        if (t.getX() > e.getX())
            xSpeed *= -1;
        if (t.getY() > e.getY())
            ySpeed *= -1;

        float rotate=0;
        if(type==ARROW) {
            float arcValue = (float) Math.atan(yDist / (float) xDist);
            rotate = (float) Math.toDegrees(arcValue);
            if (xDist < 0)
                rotate += 180;
        }

        projectiles.add(new Projectile(t.getX() + 32, t.getY() + 32, xSpeed, ySpeed, t.getDmg(),rotate, proj_id++, type));

    }

    public void update() {
        importImgs();
        for (Projectile p : projectiles)
            if (p.isActive()) {
                p.move();
                if (isProjHittingEnemy(p)) {
                    p.setActive(false);
                } else {
                    // we do nothing
                }
            }

    }

    private boolean isProjHittingEnemy(Projectile p) {
        for (Enemy e : playing.getEnemyManager().getEnemies()) {
            if (e.getBounds().contains(p.getPos())) {
                e.hurt(p.getDmg());
                if(p.getProjectileType()==CHAINS)
                    e.slow();
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics g) {
        Graphics2D g2d= (Graphics2D) g;

        for (Projectile p : projectiles)
            if (p.isActive()) {
                g2d.translate(p.getPos().x, p.getPos().y);
                g2d.rotate(Math.toRadians(p.getRotation()));
                g2d.drawImage(proj_imgs[p.getProjectileType()], -32, -32, null);
                g2d.rotate(-Math.toRadians(p.getRotation()));
                g2d.translate(-p.getPos().x, -p.getPos().y);
            }
    }

    private int getProjType(Tower t) {
        switch (t.getTowertype()) {
            case ARCHER:
                return ARROW;
            case CANNON:
                return BOMB;
            case WIZARD:
                return CHAINS;
        }
        return 0;
    }

}
