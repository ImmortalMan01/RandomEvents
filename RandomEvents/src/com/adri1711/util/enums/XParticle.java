package com.adri1711.util.enums;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public enum XParticle {
    NONE;

    public static void line(Location start, Location end, double step, ParticleDisplay display) {
        if (start.getWorld() == null || end.getWorld() == null) return;
        if (!start.getWorld().equals(end.getWorld())) return;
        Vector diff = end.toVector().subtract(start.toVector());
        double length = diff.length();
        Vector dir = diff.normalize().multiply(step);
        for (double d = 0; d <= length; d += step) {
            Location loc = start.clone().add(dir.clone().multiply(d));
            display.spawn(loc);
        }
    }

    public static void blackSun(double r1,double r2,double r3,double r4,ParticleDisplay d){
        circle(r1, 0.2, d);
    }
    public static void circle(double r, double step, ParticleDisplay d){
        for(double t=0;t<Math.PI*2;t+=step){
            double x=r*Math.cos(t); double z=r*Math.sin(t);
            d.spawn(d.getLocation().clone().add(x,0,z));
        }
    }
    public static void crescent(double r,double step, ParticleDisplay d){
        circle(r, step, d);
    }
    public static void cylinder(double h,double r,double step,ParticleDisplay d){
        for(double y=0;y<=h;y+=step){
            Location loc = d.getLocation().clone().add(0,y,0);
            for(double t=0;t<Math.PI*2;t+=step){
                double x=r*Math.cos(t); double z=r*Math.sin(t);
                d.spawn(loc.clone().add(x,0,z));
            }
        }
    }
    public static void diamond(double r,double h,double step,ParticleDisplay d){
        cylinder(h, r, step, d);
    }
    public static void ellipse(double r1,double r2,double step,ParticleDisplay d){
        for(double t=0;t<Math.PI*2;t+=step){
            double x=r1*Math.cos(t); double z=r2*Math.sin(t);
            d.spawn(d.getLocation().clone().add(x,0,z));
        }
    }
    public static void eye(double r1,double r2,double r3,double step,ParticleDisplay d){
        sphere(r1, step, d);
    }
    public static void filledCircle(double r,double density,double step,ParticleDisplay d){
        for(double rad=0; rad<=r; rad+=density){
            circle(rad, step, d);
        }
    }
    public static void illuminati(double size,double step,ParticleDisplay d){
        circle(size, step, d);
    }
    public static void infinity(double r,double step,ParticleDisplay d){
        for(double t=0;t<Math.PI*2;t+=step){
            double x = r * Math.sin(t);
            double z = r * Math.sin(t) * Math.cos(t);
            d.spawn(d.getLocation().clone().add(x,0,z));
        }
    }
    public static void ring(double r,double spacing,double step,ParticleDisplay d){
        circle(r, step, d);
    }
    public static void sphere(double r,double step,ParticleDisplay d){
        for(double phi=0; phi<=Math.PI*2; phi+=step){
            for(double theta=0; theta<=Math.PI; theta+=step){
                double x=r*Math.cos(phi)*Math.sin(theta);
                double y=r*Math.cos(theta);
                double z=r*Math.sin(phi)*Math.sin(theta);
                d.spawn(d.getLocation().clone().add(x,y,z));
            }
        }
    }
    public static void meguminExplosion(Object plugin,double size,ParticleDisplay d){
        sphere(size, 0.2, d);
    }
}
