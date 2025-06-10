package com.adri1711.util.enums;

import org.bukkit.Location;

public enum XParticle {
    NONE;
    public static void line(Location start, Location end, double step, ParticleDisplay display) {}
    public static void blackSun(double r1,double r2,double r3,double r4,ParticleDisplay d){}
    public static void circle(double r, double step, ParticleDisplay d){}
    public static void crescent(double r,double step, ParticleDisplay d){}
    public static void cylinder(double h,double r,double step,ParticleDisplay d){}
    public static void diamond(double r,double h,double step,ParticleDisplay d){}
    public static void ellipse(double r1,double r2,double step,ParticleDisplay d){}
    public static void eye(double r1,double r2,double r3,double step,ParticleDisplay d){}
    public static void filledCircle(double r,double density,double step,ParticleDisplay d){}
    public static void illuminati(double size,double step,ParticleDisplay d){}
    public static void infinity(double r,double step,ParticleDisplay d){}
    public static void ring(double r,double spacing,double step,ParticleDisplay d){}
    public static void sphere(double r,double step,ParticleDisplay d){}
    public static void meguminExplosion(Object plugin,double size,ParticleDisplay d){}
}
