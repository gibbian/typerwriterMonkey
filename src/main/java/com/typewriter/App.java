package com.typewriter;
import events.pingEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        JDABuilder jdaBuilder = JDABuilder.createDefault("MTA4NzYyODA3MDYzNjI0NTA1Mg.G2M_ZZ.2zzDG3mcbwl6smtZP-53oCMMP2eAJSMuoV6P2Y");
        jdaBuilder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        JDA jda = jdaBuilder.build();

        jda.addEventListener(new pingEvent());
    }
}
