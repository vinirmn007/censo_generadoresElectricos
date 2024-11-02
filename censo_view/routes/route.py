from flask import Blueprint, render_template, request, redirect, url_for
import requests

router = Blueprint('router', __name__)

URL = 'http://localhost:8080/myapp/'

@router.route('/')
def home():
    return render_template('home.html')