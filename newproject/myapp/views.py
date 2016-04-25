

# Create your views here.
from django.shortcuts import render
from django.http import HttpResponse
from .models import Result
import urllib
def index(request):
    return HttpResponse('<p> Wellcome! Please add /admin to your url and check the data </p>')

def scorepost(request):
    if request.method == 'POST':        
        name = str(request.POST.__getitem__(unicode('user_name')))
        # difficulty = string(request.POST['difficulty'])
        # user = CustomUser.objects.get(email=email)
        # name = 'mengyang'
        # name = str(request.POST)
        r = str(request.POST.__getitem__(unicode('result')))
        d = str(request.POST.__getitem__(unicode('difficulty')))
        aModel = Result(user_name = name, result = r, difficulty = d)
        aModel.save()
        # Highscore.objects.create(user_id = user.id, difficulty = difficulty, time = time, moves = moves, score = score)
        return HttpResponse("Score saved")
    else:
        return HttpResponse("Score not saved")
    
