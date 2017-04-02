package com.arquitectura.controller;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by @DanielQuirozV on 4/2/2017.
 */

@Controller("controladorMensajes")
@RequestMapping("/mensajes")
public class MensajeController {

    @GetMapping(value = "/mensaje")
    public ModelAndView recibeMensaje(@RequestParam(name = "mensaje", required = true) String mensaje) {
        ModelAndView mav = new ModelAndView();
        MqttClient client;
        try {
            client = new MqttClient("tcp://localhost:1883", "pahomqttpublish1");
            client.connect();
            MqttMessage message = new MqttMessage();
            System.out.println("El mensaje es: ");
            message.setPayload(mensaje.getBytes());
            client.publish("arquitecuraOpenSource/test", message);
            mav.addObject("mensaje",message);
            mav.setViewName("home");
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
        return mav;
    }
}

