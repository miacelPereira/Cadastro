package br.senai.jandira.cadastro

fun  confirmName(string:String):Boolean{
    if(string.length >=3){
        return true
    }
    return false
}


fun confirmEmail(string:String):Boolean{

    return string.contains("@")

}

fun confirmPassword(string:String):Boolean{
    if(string.length <= 3){
        return false
    }else{
        var cont:Int
        for(numero in string){
            if(numero.equals("0") or numero.equals("1") or numero.equals("2") or numero.equals("3") or numero.equals("4") or numero.equals("5") or numero.equals("6") or numero.equals("7") or numero.equals("8") or numero.equals("9") or numero.equals("10")){
                cont = numero.toInt()

            }

        }

    }

}