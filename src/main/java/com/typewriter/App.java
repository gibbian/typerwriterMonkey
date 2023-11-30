package com.typewriter;
import events.pingEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;


public class App 
{
    //Token is not included in this repo for security reasons
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        //TOKEN GOES HERE
        JDABuilder jdaBuilder = JDABuilder.createDefault("");
        jdaBuilder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        JDA jda = jdaBuilder.build();

        jda.addEventListener(new pingEvent());
    }
}
