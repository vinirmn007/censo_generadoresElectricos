from flask import Blueprint, json, render_template, request, redirect, url_for, flash
import requests

router = Blueprint('router', __name__)

URL_LINKEDLISTS = 'http://localhost:8080/myapp/'
URL_ARRAYS = 'http://localhost:8080/myapp/'

@router.route('/')
def home():
    return render_template('home.html')

#RUTAS PARA EL SISTEMA EN LINKED LIST

@router.route('/linkedList/')
def linkedList_censo():
    r = requests.get(URL_LINKEDLISTS + 'censoAPI')
    data = r.json().get('data')
    return render_template('censo.html', familiasCG=data)

@router.route('/linkedList/familias')
def linkedList_familias():
    r = requests.get(URL_LINKEDLISTS + 'censoAPI/familias')
    data = r.json().get('data')
    return render_template('familias.html', familias=data)

@router.route('/linkedList/familias/registro')
def registro_familias():
    return render_template('forms/familia/registroFamilias.html')

@router.route('/linkedList/familias/registro/save', methods=["POST"])
def save_familias():
    headers = {'Content-Type': 'application/json'}
    form = request.form
    dataForm = {"apellido": form["apellido"], "nroIntegrantes": int(form["integrantes"])}
    r = requests.post(URL_LINKEDLISTS + 'censoAPI/familias/save', data=json.dumps(dataForm), headers=headers)
    data = r.json()

    if r.status_code == 200:
        flash('Se ha guardado correctamente', category='info')
        return redirect('/linkedList/familias')
    else:
        flash('No se ha podido guardar', category='error')
        return redirect('/linkedList/familias')
    
@router.route('/linkedList/familias/edit/<id>')
def update_familias_view(id):
    r = requests.get(URL_LINKEDLISTS + 'censoAPI/familias/get/'+ id)
    data = r.json()
    if r.status_code == 200:
        return render_template('forms/familia/modificarFamilia.html', familia=data["data"])
    else:
        flash(data["data"], category='error')
        return redirect('/linkedList/familias')

@router.route('/linkedList/familias/update', methods=["POST"])
def update_familia():
    headers = {'Content-Type': 'application/json'}
    form = request.form
    dataForm = {"id": form["id"], "apellido": form["apellido"], "nroIntegrantes": int(form["integrantes"])}
    r = requests.post(URL_LINKEDLISTS + 'censoAPI/familias/update', data=json.dumps(dataForm), headers=headers)
    data = r.json()

    if r.status_code == 200:
        flash('Se ha actualizado correctamente', category='info')
        return redirect('/linkedList/familias')
    else:
        flash('No se ha podido actualizar', category='error')
        return redirect('/linkedList/familias')
    
@router.route('/linkedList/familias/delete', methods=["POST"])
def delete_familias_view():
    headers = {'Content-Type': 'application/json'}
    form = request.form
    dataForm = {"id": form["id"]}
    r = requests.post(URL_LINKEDLISTS + 'censoAPI/familias/delete', data=json.dumps(dataForm), headers=headers)

    if r.status_code == 200:
        flash('Familia eliminada exitosamente', category='info')
    else:
        data = r.json()
        flash(data["data"], category='error')
    return redirect('/linkedList/familias')

@router.route('/linkedList/familias/generador/register/<id>')
def register_generador_view(id):
    r = requests.get(URL_LINKEDLISTS + 'censoAPI/familias/get/'+ id)
    data = r.json()
    if r.status_code == 200:
        return render_template('forms/familia/modificarFamilia.html', familia=data["data"])
    else:
        flash(data["data"], category='error')
        return redirect('/linkedList/familias')

@router.route('/linkedList/familias/generador/save', methods=["POST"])
def save_generador():
    headers = {'Content-Type': 'application/json'}
    form = request.form
    dataForm = {"id": form["id"], "apellido": form["apellido"], "nroIntegrantes": int(form["integrantes"])}
    r = requests.post(URL_LINKEDLISTS + 'censoAPI/familias/update', data=json.dumps(dataForm), headers=headers)
    data = r.json()

    if r.status_code == 200:
        flash('Se ha actualizado correctamente', category='info')
        return redirect('/linkedList/familias')
    else:
        flash('No se ha podido actualizar', category='error')
        return redirect('/linkedList/familias')

@router.route('/linkedList/generadores')
def linkedList_generadores():
    r = requests.get(URL_LINKEDLISTS + 'censoAPI/generadores')
    data = r.json().get('data')
    return render_template('generadores.html', generadores=data)

#RUTAS PARA EL SISTEMA EN ARRAYS

@router.route('/arrays/')
def arrays_censo():
    return render_template('censo.html')

@router.route('/arrays/familias')
def arrays_familias():
    return render_template('familias.html')

@router.route('/arrays/generadores')
def arrays_generadores():
    return render_template('generadores.html')